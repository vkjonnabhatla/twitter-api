package com.twitter.api.twitterapi.mapper;

import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;


public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDtos(List<E> entities);
}
