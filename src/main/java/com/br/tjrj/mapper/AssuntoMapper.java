package com.br.tjrj.mapper;

import com.br.tjrj.dto.AssuntoDTO;
import com.br.tjrj.model.Assunto;
import org.mapstruct.Mapper;

/**
 * Mapper para assunto
 * para conversão de DTO para entidade e vice-versa
 * {@link Mapper}
 */
@Mapper(componentModel = "spring")
public interface AssuntoMapper  extends EntityMapper<Assunto, AssuntoDTO, Long>{

    default AssuntoDTO withId(AssuntoDTO dto, Long id) {
        return new AssuntoDTO(id, dto.descricao());
    }
}
