package com.br.tjrj.controller;

import com.br.tjrj.dto.interfaces.EntityDTO;
import com.br.tjrj.mapper.EntityMapper;
import com.br.tjrj.service.GenericServiceAbstract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe abstrata para operações de CRUD
 * @param <T> entidade
 * @param <DTO> dto
 * @param <PK> tipo do identificador
 * @param <SERVICE> serviço
 * @param <MAPPER> mapeador
 */

public abstract class AbstractController<T extends Entity,
        DTO extends EntityDTO, PK, SERVICE extends GenericServiceAbstract<T , PK>,
        MAPPER extends EntityMapper<T , DTO, PK>> {

    protected final SERVICE service;

    protected final MAPPER mapper;

    public AbstractController(SERVICE service, MAPPER mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /** Método para criar um registro
     * @param dto registro a ser criado
     * @return registro criado
     */
    @Operation(summary = "Cria um novo registro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto) {
        T entity = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDto(entity), HttpStatus.CREATED);
    }

    /**
     * Método para atualizar um registro
     * @param dto registro a ser atualizado
     * @return registro atualizado
     */
    @Operation(summary = "Atualiza um registro")
    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable PK id, @Valid @RequestBody DTO dto) {
        DTO dtoComId = mapper.withId(dto, id);
        T entity = service.update(mapper.toEntity(dtoComId));
        return ResponseEntity.ok(mapper.toDto(entity));
    }
    /**
     * Método para deletar um registro por id
     * @param id identificador do registro
     * @return status da operação
     */
    @Operation(summary = "Deleta um registro por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable PK id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método para buscar um registro por id
     * @param id identificador do registro
     * @return registro
     */
    @Operation(summary = "Busca um registro por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable PK id) {
        Optional<T> entityOptional = service.findById(id);
        return entityOptional.map(entity -> ResponseEntity.ok(mapper.toDto(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Método para buscar todos os registros
     * @return lista de registros
     */
    @Operation(summary = "Lista todos os registros")
    @GetMapping
    public ResponseEntity<List<DTO>> findAll() {
        List<T> entities = service.findAll();
        return ResponseEntity.ok(mapper.toDtos(entities));
    }
}
