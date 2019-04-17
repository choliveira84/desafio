package com.pitang.desafio.api.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.pitang.desafio.api.model.entity.Telefone;
import com.pitang.desafio.api.model.entity.Usuario;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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

  @Getter
  private List<TelefoneDTO> phones;

  public static UsuarioDTO converterParaDTO(Usuario usuario) {
    return new UsuarioDTO(usuario.getFirstName(), usuario.getLastName(), usuario.getUsername(),
        usuario.getPassword(), usuario.getCreated_at(), usuario.getLast_login(),
        crairListaTelefone(usuario));
  }

  private static List<TelefoneDTO> crairListaTelefone(Usuario usuario) {
    List<TelefoneDTO> returnList = new ArrayList<>();

    usuario.getPhones().stream().forEach(t -> {
      returnList.add(converterTelefone(t));
    });

    return returnList;
  }

  private static TelefoneDTO converterTelefone(Telefone telefoneUsuario) {
    TelefoneDTO telefoneInsercao = new TelefoneDTO();

    BeanUtils.copyProperties(telefoneUsuario, telefoneInsercao);

    return telefoneInsercao;
  }


  @Getter
  @Setter
  @ToString
  private static class TelefoneDTO {

    private String number;

    private String area_code;

    private String country_code;
  }
}