package com.pitang.desafio.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -6251471203187753547L;

  public ResourceNotFoundException(String messagem) {
    super(messagem);
  }

}