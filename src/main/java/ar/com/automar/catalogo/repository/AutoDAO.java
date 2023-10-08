package ar.com.automar.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.automar.catalogo.model.Auto;

@Repository
public interface AutoDAO extends JpaRepository<Auto, Long> {

}
