package com.pitang.desafio.api.controller;

import java.security.Principal;
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
public class UsuarioDTO {

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final String email;

    @Getter(value = AccessLevel.PRIVATE)
    private final String password;

    @Getter
    private LocalDateTime created_at;

    @Getter
    private LocalDateTime last_login;

    public static UsuarioDTO converterParaDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getFirstName(), usuario.getLastName(), usuario.getUsername(),
                usuario.getPassword(), usuario.getCreated_at(), usuario.getLast_login());
    }

}