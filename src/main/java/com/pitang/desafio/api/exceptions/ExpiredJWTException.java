package com.pitang.desafio.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 *
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExpiredJWTException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ExpiredJWTException(String messagem) {
    super(messagem);
  }

}
