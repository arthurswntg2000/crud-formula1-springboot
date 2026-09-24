// Criado (24/09/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Carro;
import com.f1.crud.dto.CarroRequestDTO;
import com.f1.crud.dto.CarroResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CarroMapper extends GenericMapper<Carro, CarroRequestDTO, CarroResponseDTO> {

    @Override
    @Mapping(source = "escuderia.nome", target = "nomeEscuderia")
    CarroResponseDTO toDto(Carro entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderia", ignore = true)
    Carro toEntity(CarroRequestDTO dto);
}