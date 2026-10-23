// Criado (24/09/2026); // Atualizações (02/10/2026); (22/10/2026); (23/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.EscuderiaResponseDTO;
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

    @Mapping(target = "escuderias", expression = "java(mapEscuderiasToDtos(piloto.getEscuderias()))")
    PilotoResponseDTO toDto(Piloto piloto);

    List<PilotoResponseDTO> toDtoList(List<Piloto> pilotos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "escuderias", ignore = true)
    void updateEntityFromDto(PilotoRequestDTO dto, @MappingTarget Piloto piloto);

    // Método manual para converter Set<Escuderia> para Set<EscuderiaResponseDTO> sem precisar injetar EscuderiaMapper
    default Set<EscuderiaResponseDTO> mapEscuderiasToDtos(Set<Escuderia> escuderias) {
        if (escuderias == null) return null;
        return escuderias.stream()
                .map(e -> new EscuderiaResponseDTO(
                        e.getId(),
                        e.getNome(),
                        e.getPaisOrigem(),
                        e.getDataFundacao(),
                        e.getFundador(),
                        e.getTitulos(),
                        e.getBiografia(),
                        e.getUrlLogo(),
                        null, // evita recursão infinita ao mapear os pilotos dentro da escuderia do piloto
                        null
                ))
                .collect(Collectors.toSet());
    }
}