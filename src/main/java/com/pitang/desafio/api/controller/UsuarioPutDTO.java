package com.pitang.desafio.api.controller;

import com.pitang.desafio.api.model.entity.Usuario;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * UsuarioPostDTO
 */
@Setter
public class UsuarioPutDTO {

    @Getter
    private Long id;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private String username;

    @Getter(value = AccessLevel.PRIVATE)
    private String password;

    public static Usuario converterParaTO(UsuarioPutDTO usuario) {
        Usuario usuarioAtualizaco = new Usuario();

        usuarioAtualizaco.setId(usuario.getId());
        usuarioAtualizaco.setEmail(usuario.getUsername());
        usuarioAtualizaco.setPassword(usuario.getPassword());
        usuarioAtualizaco.setLastName(usuario.getLastName());
        usuarioAtualizaco.setFirstName(usuario.getFirstName());

        return usuarioAtualizaco;
    }
}