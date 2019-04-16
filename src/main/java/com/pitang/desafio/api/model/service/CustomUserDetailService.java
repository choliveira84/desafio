package com.pitang.desafio.api.model.service;

import java.util.Optional;

import com.pitang.desafio.api.model.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * CustomUserDetailService
 */
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "Invalid field 'username'");
        Usuario usuario = Optional.ofNullable(usuarioService.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid e-mail or password."));

        return new User(//
                usuario.getUsername(), //
                usuario.getPassword(), //
                AuthorityUtils.createAuthorityList("ROLER_USER"));
    }

}