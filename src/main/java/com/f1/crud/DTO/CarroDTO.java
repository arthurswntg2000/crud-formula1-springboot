package com.f1.crud.dto;       // Criado (23/09/2026)

import com.f1.crud.domain.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroDTO {

    
    private String modelo;
    private String motor;
    private Integer ano;
    private String nomeEscuderia; // Apenas o nome da escuderia, não o objeto inteiro

    public CarroDTO(Carro carro) {
        this.modelo = carro.getModelo();
        this.motor = carro.getMotor();
        this.ano = carro.getAno();
        if (carro.getEscuderia() != null) {
            this.nomeEscuderia = carro.getEscuderia().getNome();
        }
    }
}
