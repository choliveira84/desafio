package com.pitang.desafio.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.pitang.desafio.api.model.entity.Telefone;
import com.pitang.desafio.api.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UsuarioPostDTO
 */
@Setter
@AllArgsConstructor
@ToString
public class UsuarioPostDTO {

  @Getter
  private final String firstName;

  @Getter
  private final String lastName;

  @Getter
  private final String email;

  @Getter
  private final String password;

  @Getter
  private final List<TelefonePostDTO> phones;

  public static Usuario converterParaTO(UsuarioPostDTO usuario) {
    Usuario usuarioInsercao = new Usuario();

    usuarioInsercao.setEmail(usuario.getEmail());
    usuarioInsercao.setPassword(usuario.getPassword());
    usuarioInsercao.setLastName(usuario.getLastName());
    usuarioInsercao.setFirstName(usuario.getFirstName());
    usuarioInsercao.setPhones(crairListaTelefone(usuario, usuarioInsercao));

    return usuarioInsercao;
  }

  private static List<Telefone> crairListaTelefone(UsuarioPostDTO usuarioPostDTO,
      Usuario usuarioInsercao) {
    List<Telefone> returnList = new ArrayList<>();

    usuarioPostDTO.getPhones().stream().forEach(t -> {
      returnList.add(converterTelefone(t));
    });

    return returnList;
  }

  private static Telefone converterTelefone(TelefonePostDTO telefoneUsuario) {
    Telefone telefoneInsercao = new Telefone();

    BeanUtils.copyProperties(telefoneUsuario, telefoneInsercao);

    return telefoneInsercao;
  }

}