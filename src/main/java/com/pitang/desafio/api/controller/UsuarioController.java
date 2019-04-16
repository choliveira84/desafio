package com.pitang.desafio.api.controller;

import java.security.Principal;

import com.pitang.desafio.api.model.entity.Usuario;
import com.pitang.desafio.api.model.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
@Controller
@RequestMapping()
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/signup")
    public ResponseEntity<?> signup(@RequestBody UsuarioPostDTO dto) throws AuthenticationException {
        String token = usuarioService.signup(UsuarioPostDTO.converterParaTO(dto));
        return ResponseEntity.ok(token);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<String> signin(@RequestBody UsuarioSignin usuarioSignin) {
        String token = usuarioService.signin(UsuarioSignin.converterParaTO(usuarioSignin));
        return ResponseEntity.ok(token);
    }

    @GetMapping(path = "/me")
    public ResponseEntity<UsuarioDTO> me(Principal principal) {
        return ResponseEntity.ok(UsuarioDTO.converterParaDTO(usuarioService.findByUsername(principal.getName())));
    }

}