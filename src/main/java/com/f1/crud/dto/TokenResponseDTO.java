// Criado (28/09/2026); Atualizações (09/10/2026)
package com.f1.crud.dto;

import com.f1.crud.domain.Usuario;

public record TokenResponseDTO(
    String token,
    String type,
    String login,
    String nome
) {
    // Construtor auxiliar para facilitar no Controller
    public TokenResponseDTO(String token, Usuario usuario) {
        this(token, "Bearer", usuario.getLogin(), usuario.getNome());
    }
}
