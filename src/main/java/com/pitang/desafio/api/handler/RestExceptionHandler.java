package com.pitang.desafio.api.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pitang.desafio.api.exceptions.ErrorDetails;
import com.pitang.desafio.api.exceptions.ExpiredJWTException;
import com.pitang.desafio.api.exceptions.ResourceAlreadyExistsException;
import com.pitang.desafio.api.exceptions.ResourceNotFoundException;
import com.pitang.desafio.api.exceptions.ValidationErrorDetails;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
                ErrorDetails details = ErrorDetails.builder()//
                                .status(HttpStatus.NOT_FOUND.value())//
                                .message(exception.getMessage())//
                                .build();

                return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ResourceAlreadyExistsException.class)
        public ResponseEntity<?> handleResourceNotFoundException(ResourceAlreadyExistsException exception) {
                ErrorDetails details = ErrorDetails.builder()//
                                .status(HttpStatus.CONFLICT.value())//
                                .message(exception.getMessage())//
                                .build();

                return new ResponseEntity<>(details, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(PropertyReferenceException.class)
        public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException exception) {
                ErrorDetails details = ErrorDetails.builder()//
                                .status(HttpStatus.BAD_REQUEST.value())//
                                .message(exception.getMessage())//
                                .build();

                return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception) {
                ErrorDetails details = ErrorDetails.builder()//
                                .status(HttpStatus.BAD_REQUEST.value())//
                                .message(exception.getMessage())//
                                .build();

                return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exception) {
                ErrorDetails details = ErrorDetails.builder()//
                                .status(HttpStatus.FORBIDDEN.value())//
                                .message("Unauthorized")//
                                .build();

                return new ResponseEntity<>(details, HttpStatus.FORBIDDEN);
        }

        @ExceptionHandler(ExpiredJWTException.class)
        public ResponseEntity<?> handleExpiredJwtException(ExpiredJWTException exception) {
                ErrorDetails details = ErrorDetails.builder()//
                                .status(HttpStatus.FORBIDDEN.value())//
                                .message("Unauthorized - invalid session")//
                                .build();

                return new ResponseEntity<>(details, HttpStatus.FORBIDDEN);
        }

        @Override
        public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {
                List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

                String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage)//
                                .distinct().collect(Collectors.joining(", "));

                ValidationErrorDetails details = ValidationErrorDetails.builder()//
                                .status(HttpStatus.BAD_REQUEST.value())//
                                .message(fieldMessages)//
                                .build();

                return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {

                ErrorDetails details = ErrorDetails.builder()//
                                .status(status.value())//
                                .message("Internal Error")//
                                .build();

                return new ResponseEntity<>(details, headers, status);
        }

}