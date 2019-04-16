package com.pitang.desafio.api.controller;

import com.pitang.desafio.api.model.entity.Usuario;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * UsuarioPostDTO
 */
@Setter
@AllArgsConstructor
public class UsuarioPostDTO {

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final String email;

    @Getter
    private final String password;

    public static Usuario converterParaTO(UsuarioPostDTO usuario) {
        Usuario usuarioAtualizaco = new Usuario();

        usuarioAtualizaco.setEmail(usuario.getEmail());
        usuarioAtualizaco.setPassword(usuario.getPassword());
        usuarioAtualizaco.setLastName(usuario.getLastName());
        usuarioAtualizaco.setFirstName(usuario.getFirstName());

        return usuarioAtualizaco;
    }

}