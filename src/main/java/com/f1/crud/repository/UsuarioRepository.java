// Criado (07/10/2026)
package com.f1.crud.repository;

import com.f1.crud.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
    boolean existsByLogin(String login);
}