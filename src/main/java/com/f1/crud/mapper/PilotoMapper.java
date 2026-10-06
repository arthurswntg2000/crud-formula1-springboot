// Criado (24/09/2026); // Atualizado (02/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoRequestDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PilotoMapper {
   
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    Piloto toEntity(PilotoRequestDTO dto);

    // Criado (02/10/2026)
    @Mapping(target = "nomesEscuderias", expression = "java(mapEscuderiasToNomes(piloto.getEscuderias()))")
    PilotoResponseDTO toDto(Piloto piloto);

    List<PilotoResponseDTO> toDtoList(List<Piloto> pilotos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    void updateEntityFromDto(PilotoRequestDTO dto, @MappingTarget Piloto piloto);

    // Atualizado (06/10/2026)
    default Set<String> mapEscuderiasToNomes(Set<Escuderia> escuderias) {
        if (escuderias == null) return null;
        return escuderias.stream().map(Escuderia::getNome).collect(Collectors.toSet());
    }
}