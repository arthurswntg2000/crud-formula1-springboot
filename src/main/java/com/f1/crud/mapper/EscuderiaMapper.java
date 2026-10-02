// Criado (24/09/2026); // Atualizado (02/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EscuderiaMapper {

    // Criado (02/10/2026)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carros", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    Escuderia toEntity(EscuderiaRequestDTO dto);

    EscuderiaResponseDTO toDto(Escuderia escuderia);

    List<EscuderiaResponseDTO> toDtoList(List<Escuderia> escuderias);


    // Atualizado (02/10/2026)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    @Mapping(target = "carros", ignore = true)
    void updateEntityFromDto(EscuderiaRequestDTO dto, @MappingTarget Escuderia escuderia);
}