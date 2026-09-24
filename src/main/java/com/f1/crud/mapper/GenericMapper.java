// criado (24/09/2026)
package com.f1.crud.mapper;

import org.mapstruct.MappingTarget;
import java.util.List;

public interface GenericMapper<E, REQ, RES> {

    // Converte DTO de entrada (Request) para a Entidade de domínio
    E toEntity(REQ dto);

    // Converte a Entidade para o DTO de saída (Response)
    RES toDto(E entity);

    // Converte uma lista de Entidades para uma lista de DTOs de saída
    List<RES> toDtoList(List<E> entityList);

    // Converte uma lista de DTOs de entrada para uma lista de Entidades
    List<E> toEntityList(List<REQ> dtoList);

    // Atualiza os campos de uma Entidade existente com os dados de um DTO (operações de PUT/PATCH)
    void updateEntityFromDto(REQ dto, @MappingTarget E entity);
}