package com.br.tjrj.mapper;

import com.br.tjrj.dto.LivroDTO;
import com.br.tjrj.model.Livro;
import org.mapstruct.Mapper;

/**
 * Mapper para livro
 * para conversão de DTO para entidade e vice-versa
 * {@link Mapper}
 */
@Mapper(componentModel = "spring")
public interface LivroMapper extends EntityMapper<Livro, LivroDTO, Long> {

    default LivroDTO withId(LivroDTO dto, Long id) {
        return new LivroDTO(id, dto.titulo(), dto.editora(), dto.edicao(),
                dto.anoPublicacao(), dto.valor(), dto.dataCriacao(),
                dto.dataAlteracao(), dto.autores(), dto.assuntos());
    }
}
