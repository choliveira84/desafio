package com.pitang.desafio.api.model.service;

import com.pitang.desafio.api.model.entity.Usuario;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
public interface UsuarioService {

    Usuario findByUsername(String username);

    String signup(Usuario usuario);

    String signin(Usuario usuario);

    boolean exists(Long id);
}