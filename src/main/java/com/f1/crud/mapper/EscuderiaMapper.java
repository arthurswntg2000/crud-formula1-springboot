// Criado (24/09/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EscuderiaMapper extends GenericMapper<Escuderia, EscuderiaRequestDTO, EscuderiaResponseDTO> {

    @Override
    EscuderiaResponseDTO toDto(Escuderia entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    @Mapping(target = "carros", ignore = true)
    Escuderia toEntity(EscuderiaRequestDTO dto);
}