// Criado (28/09/2026)
package com.f1.crud.controller;

import com.f1.crud.domain.Usuario;
import com.f1.crud.dto.LoginDTO;
import com.f1.crud.dto.RegistroDTO;
import com.f1.crud.dto.TokenResponseDTO;
import com.f1.crud.dto.UsuarioResponseDTO;
import com.f1.crud.repository.UsuarioRepository;
import com.f1.crud.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@RequestBody @Valid LoginDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = authenticationManager.authenticate(authToken);

        var tokenJWT = tokenService.gerarToken(dto.login());

        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT));
    }

    // Criado (07/10/2026) 
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroDTO dto) {
        if (usuarioRepository.existsByLogin(dto.login())) {
            return ResponseEntity.badRequest().body("Erro: E-mail/Login já cadastrado!");
        }

        String senhaCriptografada = passwordEncoder.encode(dto.password());

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setLogin(dto.login());
        novoUsuario.setPassword(senhaCriptografada);

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

    // Criado (09/10/2026)
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        var usuarios = usuarioRepository.findAll()
            .stream()
            .map(UsuarioResponseDTO::new)
            .toList();
            
        return ResponseEntity.ok(usuarios);
}
}