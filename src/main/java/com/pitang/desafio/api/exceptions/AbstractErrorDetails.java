package com.pitang.desafio.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
@Getter
@AllArgsConstructor
abstract class AbstractErrorDetails {
    private final String message;
    private final Integer status;
}