// Criado (09/10/2026)
package com.f1.crud.dto;

import com.f1.crud.domain.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String login
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin());
    }
}