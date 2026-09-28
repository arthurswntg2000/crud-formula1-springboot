// Criado (28/09/2026)
package com.f1.crud.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret:sua-chave-secreta-super-segura-f1-manager-2026}")
    private String secret;

    @Value("${api.security.token.expiration:86400000}") // Ou seja, expira em 24 horas (em ms)
    private Long expiration;

    private SecretKey getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken(String username) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(agora)
                .expiration(dataExpiracao)
                .signWith(getSigningKey())
                .compact();
    }

    public String validarToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            
            return claims.getSubject();
        } catch (Exception e) {
            return null; // Token inválido ou expirado
        }
    }
}