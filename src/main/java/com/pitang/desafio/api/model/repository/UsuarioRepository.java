package com.pitang.desafio.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pitang.desafio.api.model.entity.Usuario;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select * FROM usuario where email = :username", nativeQuery = true)
    Usuario findByUsername(String username);
}