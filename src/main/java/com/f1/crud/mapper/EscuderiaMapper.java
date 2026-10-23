// Criado (24/09/2026); Atualizações (02/10/2026); (23/10/2026)
package com.f1.crud.mapper;

import com.f1.crud.domain.Carro;
import com.f1.crud.domain.Escuderia;
import com.f1.crud.domain.Piloto;
import com.f1.crud.dto.CarroResumoDTO;
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
    @Mapping(target = "carros", expression = "java(mapCarrosToResumoDtos(escuderia.getCarros()))")
    EscuderiaResponseDTO toDto(Escuderia escuderia);

    List<EscuderiaResponseDTO> toDtoList(List<Escuderia> escuderias);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pilotos", ignore = true)
    @Mapping(target = "carros", ignore = true)
    void updateEntityFromDto(EscuderiaRequestDTO dto, @MappingTarget Escuderia escuderia);

    // Mapeamento manual de Pilotos sem recursão
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
                        null // Apenas passe o argumento null diretamente
                ))
                .collect(Collectors.toSet());
    }

    // Mapeamento manual de Carros para CarroResumoDTO com os 5 parâmetros requeridos pelo Record
    default Set<CarroResumoDTO> mapCarrosToResumoDtos(List<Carro> carros) {
        if (carros == null) return null;
        return carros.stream()
                .map(c -> new CarroResumoDTO(
                        c.getId(),
                        c.getModelo(),
                        c.getMotor(),
                        c.getAno(),
                        c.getUrlFoto() // ou null/c.getEscuderia().getNome()
                ))
                .collect(Collectors.toSet());
    }
}