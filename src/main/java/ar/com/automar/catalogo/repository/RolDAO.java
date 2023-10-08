package ar.com.automar.catalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.automar.catalogo.model.Rol;

@Repository
public interface RolDAO extends JpaRepository<Rol,Long> {

    Optional<Rol> findByNombre(String nombre);
    
}
