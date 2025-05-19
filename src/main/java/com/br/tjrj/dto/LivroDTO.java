package com.br.tjrj.dto;

import com.br.tjrj.dto.interfaces.EntityDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe para representação de livro
 * para operações de api rest
 */
public record LivroDTO(
        Long id,

        @NotBlank(message = "notNull.titulo")
        @Size(min = 3, max = 40, message = "error.livro.invalidTitulo.length")
        String titulo,

        @Size(max = 40, message = "error.livro.invalidEditora.length")
        String editora,

        Integer edicao,

        @Size(max = 4, message = "error.livro.invalidAnoPublicacao.length")
        String anoPublicacao,

        @NotNull(message = "notNull.valor")
        BigDecimal valor,

        LocalDateTime dataCriacao,
        LocalDateTime dataAlteracao,

        @Valid
        @JsonManagedReference
        List<AutorDTO> autores,

        @Valid
        @JsonManagedReference
        List<AssuntoDTO> assuntos

) implements EntityDTO {

    @Override
    public Long getId() {
        return this.id;
    }

}