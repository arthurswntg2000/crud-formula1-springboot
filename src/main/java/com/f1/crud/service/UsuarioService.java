// Criado (09/10/2026)
package com.f1.crud.service;

import com.f1.crud.dto.RegistroDTO;
import com.f1.crud.dto.UsuarioResponseDTO;
import java.util.List;

public interface UsuarioService {
    void registrar(RegistroDTO dto);
    List<UsuarioResponseDTO> listarTodos();
    UsuarioResponseDTO buscarPorId(Long id);
    void deletar(Long id);
}