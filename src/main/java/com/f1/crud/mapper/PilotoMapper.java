// Criado (24/09/2026); // Atualizado (02/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.PilotoRequestDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PilotoMapper {
   
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    Piloto toEntity(PilotoRequestDTO dto);

    // Criado (02/10/2026)
    @Mapping(target = "idade", expression = "java(calcularIdade(piloto.getDataNascimento()))")
    @Mapping(target = "escuderiasIds", expression = "java(mapEscuderiasToIds(piloto.getEscuderias()))")
    PilotoResponseDTO toDto(Piloto piloto);

    List<PilotoResponseDTO> toDtoList(List<Piloto> pilotos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    void updateEntityFromDto(PilotoRequestDTO dto, @MappingTarget Piloto piloto);

    // Criado (02/10/2026)
    default Integer calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) return null;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    // Atualizado (02/10/2026)
    default Set<Long> mapEscuderiasToIds(Set<Escuderia> escuderias) {
        if (escuderias == null) return null;
        return escuderias.stream().map(Escuderia::getId).collect(Collectors.toSet());
    }
}