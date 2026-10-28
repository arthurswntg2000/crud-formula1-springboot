// Criado (24/09/2026);  Atualizado (02/10/2026); (27/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Carro;
import com.f1.crud.dto.CarroRequestDTO;
import com.f1.crud.dto.CarroResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PilotoMapper.class})
public interface CarroMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderia", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    Carro toEntity(CarroRequestDTO dto);

    @Mapping(target = "nomeEscuderia", source = "escuderia.nome")
    @Mapping(target = "escuderiaId", source = "escuderia.id")
    CarroResponseDTO toDto(Carro carro);

    List<CarroResponseDTO> toDtoList(List<Carro> carros);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderia", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    void updateEntityFromDto(CarroRequestDTO dto, @MappingTarget Carro carro);
}