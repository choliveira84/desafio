package com.pitang.desafio.api.controller;

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
public class TelefonePostDTO {

  @Getter
  private final String number;

  @Getter
  private final String area_code;

  @Getter
  private final String country_code;

  @Getter
  private final UsuarioPostDTO usuario;

}