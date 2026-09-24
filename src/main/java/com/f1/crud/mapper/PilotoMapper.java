// Criado (24/09/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoRequestDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PilotoMapper extends GenericMapper<Piloto, PilotoRequestDTO, PilotoResponseDTO> {

    @Override
    @Mapping(source = "escuderia.nome", target = "nomeEscuderia")
    PilotoResponseDTO toDto(Piloto entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderia", ignore = true)
    Piloto toEntity(PilotoRequestDTO dto);
}