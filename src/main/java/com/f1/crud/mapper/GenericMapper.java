// criado (24/09/2026)
package com.f1.crud.mapper;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import java.util.List;

public interface GenericMapper<E, REQ, RES> {

    // Converte DTO de entrada (Request) para a Entidade de domínio
    E toEntity(REQ dto);

    // Converte a Entidade para o DTO de saída (Response)
    RES toDto(E entity);
    
    // Converte uma lista de Entidades para uma lista de DTOs de resposta
    List<RES> toDtoList(List<E> entityList);

    // Converte uma lista de DTOs de entrada para uma lista de Entidades
    List<E> toEntityList(List<REQ> dtoList);

    // Atualiza a instância existente da Entidade com dados do DTO de requisição
    void updateEntityFromDto(REQ dto, @MappingTarget E entity);

    // Método utilitário para conversão de páginas do Spring Data
    default Page<RES> toDtoPage(Page<E> entityPage) {
        return entityPage.map(this::toDto);
    }
}