package com.pitang.desafio.api.model.service;

import java.time.LocalDateTime;

import com.pitang.desafio.api.config.JWTUtil;
import com.pitang.desafio.api.exceptions.ResourceAlreadyExistsException;
import com.pitang.desafio.api.exceptions.ResourceNotFoundException;
import com.pitang.desafio.api.model.entity.Usuario;
import com.pitang.desafio.api.model.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
@Service
class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    public Usuario findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Transactional
    @Override
    public String signup(Usuario usuario) {
        if (findByUsername(usuario.getUsername()) != null) {
            throw new ResourceAlreadyExistsException(String.format("E-mail already exists.", usuario.getId()));
        }

        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));

        if (usuario.getId() != null && exists(usuario.getId())) {
            throw new ResourceAlreadyExistsException(String.format("User already exists.", usuario.getId()));
        }

        Usuario usuarioSalvo = repository.save(usuario);

        return JWTUtil.generateToken(usuarioSalvo.getUsername());
    }

    @Transactional
    @Override
    public String signin(Usuario usuario) {
        Usuario usuarioEncontrado = findByUsername(usuario.getUsername());
       
        if(usuarioEncontrado == null){
            throw new ResourceNotFoundException("Invalid e-mail or password.");
        }

        usuarioEncontrado.setLast_login(LocalDateTime.now());
       
        Usuario usuarioSalvo = repository.save(usuarioEncontrado);
        
        return JWTUtil.generateToken(usuarioSalvo.getUsername());
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}