// Criado (28/09/2026)
package com.f1.crud.controller;

import com.f1.crud.dto.LoginDTO;
import com.f1.crud.dto.TokenResponseDTO;
import com.f1.crud.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> efecutarLogin(@RequestBody @Valid LoginDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = authenticationManager.authenticate(authToken);

        var tokenJWT = tokenService.gerarToken(dto.login());

        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT));
    }
}