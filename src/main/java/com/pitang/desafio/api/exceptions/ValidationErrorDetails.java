package com.pitang.desafio.api.exceptions;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
@Getter
public class ValidationErrorDetails extends AbstractErrorDetails {

    @Builder
    public ValidationErrorDetails(String message, Integer status) {
        super(message, status);
    }
}
