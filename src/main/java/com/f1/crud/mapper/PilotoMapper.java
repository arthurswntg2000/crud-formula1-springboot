// Criado (24/09/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoRequestDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PilotoMapper extends GenericMapper<Piloto, PilotoRequestDTO, PilotoResponseDTO> {

    @Override
    @Mapping(source = "escuderias", target = "nomesEscuderias", qualifiedByName = "mapearNomesEscuderias")
    PilotoResponseDTO toDto(Piloto entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    Piloto toEntity(PilotoRequestDTO dto);

    @Named("mapearNomesEscuderias")
    default Set<String> mapearNomesEscuderias(Set<Escuderia> escuderias) {
        if (escuderias == null) return Set.of();
        return escuderias.stream()
                .map(Escuderia::getNome)
                .collect(Collectors.toSet());
    }
}