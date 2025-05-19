package com.br.tjrj.mapper;

import com.br.tjrj.dto.interfaces.EntityDTO;

import java.util.List;

/**
 * Interface para mapeamento de entidades e DTOs.
 * @param <E> Entidade
 * @param <D> DTO
 * @param <ID> Tipo do ID
 */
public interface EntityMapper<E, D extends EntityDTO, ID> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntities(List<D> dtoList);
    List<D> toDtos(List<E> entityList);
    D withId(D dto, ID id);
}