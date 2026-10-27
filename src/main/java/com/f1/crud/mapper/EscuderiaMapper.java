// Criado (24/09/2026); Atualizações (02/10/2026); (23/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.CarroResponseDTO;
import com.f1.crud.dto.EscuderiaRequestDTO;
import com.f1.crud.dto.EscuderiaResponseDTO;
import com.f1.crud.dto.PilotoResponseDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EscuderiaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carros", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    Escuderia toEntity(EscuderiaRequestDTO dto);

    @Mapping(target = "pilotos", expression = "java(mapPilotosToDtos(escuderia.getPilotos()))")
    @Mapping(target = "carros", expression = "java(mapCarrosToDtos(escuderia.getCarros()))")
    EscuderiaResponseDTO toDto(Escuderia escuderia);

    List<EscuderiaResponseDTO> toDtoList(List<Escuderia> escuderias);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    @Mapping(target = "carros", ignore = true)
    void updateEntityFromDto(EscuderiaRequestDTO dto, @MappingTarget Escuderia escuderia);

    // Mapeamento manual de Pilotos
    default Set<PilotoResponseDTO> mapPilotosToDtos(Set<Piloto> pilotos) {
        if (pilotos == null) return null;
        return pilotos.stream()
                .map(p -> new PilotoResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getNacionalidade(),
                        p.getNumeroCarro(),
                        p.getDataNascimento(),
                        p.getTitulos(),
                        p.getStatus(),
                        p.getBiografia(),
                        p.getUrlFoto(),
                        null
                ))
                .collect(Collectors.toSet());
    }

    // Mapeamento manual de Carros para CarroResponseDTO
    default Set<CarroResponseDTO> mapCarrosToDtos(List<Carro> carros) {
        if (carros == null) return null;
        return carros.stream()
                .map(c -> new CarroResponseDTO(
                        c.getId(),
                        c.getModelo(),
                        c.getMotor(),
                        c.getAno(),
                        c.getUrlFoto(),
                        c.getFichaTecnica(),
                        c.getEscuderia() != null ? c.getEscuderia().getNome() : null,
                        null
                ))
                .collect(Collectors.toSet());
    }
}