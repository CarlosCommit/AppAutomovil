package ar.com.automar.catalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.automar.catalogo.model.Usuario;
@Repository
public interface UsuarioDAO extends JpaRepository <Usuario, Long>  {
    Optional<Usuario> findByUsername(String username);
}
