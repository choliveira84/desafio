package com.pitang.desafio.api.controller;

import java.time.LocalDateTime;

import com.pitang.desafio.api.model.entity.Usuario;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * UsuarioPostDTO
 */
@AllArgsConstructor
@ToString
public class UsuarioSignin {

    @Getter
    private final String email;

    @Getter
    private final String password;

    public static UsuarioSignin converterParaDTO(Usuario usuario) {
        return new UsuarioSignin(usuario.getEmail(), usuario.getPassword());
    }

    public static Usuario converterParaTO(UsuarioSignin usuarioSignin) {
        Usuario usuario = new Usuario();

        usuario.setPassword(usuarioSignin.getPassword());
        usuario.setEmail(usuarioSignin.getEmail());

        return usuario;
    }

}