// Criado (28/09/2026); Atualizações (09/10/2026)
package com.f1.crud.controller;

import com.f1.crud.domain.Usuario;
import com.f1.crud.dto.LoginDTO;
import com.f1.crud.dto.RegistroDTO;
import com.f1.crud.dto.TokenResponseDTO;
import com.f1.crud.dto.UsuarioResponseDTO;
import com.f1.crud.security.TokenService;
import com.f1.crud.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@RequestBody @Valid LoginDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();
        
        var tokenJWT = tokenService.gerarToken(usuario.getLogin());

        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT, usuario));
    }

    // Criado (07/10/2026) 
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroDTO dto) {
        usuarioService.registrar(dto);
        return ResponseEntity.ok().build();
    }

    // Criado (09/10/2026)
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        var usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // Criado (09/10/2026)
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}