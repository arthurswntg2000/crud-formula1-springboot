// Criado (24/09/2026); // Atualizado (02/10/2026); (22/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoRequestDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EscuderiaMapper.class})
public interface PilotoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    Piloto toEntity(PilotoRequestDTO dto);

    PilotoResponseDTO toDto(Piloto piloto);

    List<PilotoResponseDTO> toDtoList(List<Piloto> pilotos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    void updateEntityFromDto(PilotoRequestDTO dto, @MappingTarget Piloto piloto);
}