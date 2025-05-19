package com.br.tjrj.mapper;

import com.br.tjrj.dto.AutorDTO;
import com.br.tjrj.model.Autor;
import org.mapstruct.Mapper;

/**
 * Mapper para autor
 * para conversão de DTO para entidade e vice-versa
 * {@link Mapper}
 */
@Mapper(componentModel = "spring")
public interface AutorMapper extends EntityMapper<Autor, AutorDTO, Long> {

    default AutorDTO withId(AutorDTO dto, Long id) {
        return new AutorDTO(id, dto.nome());
    }
}
