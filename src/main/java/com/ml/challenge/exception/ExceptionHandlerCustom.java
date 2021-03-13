package com.ml.challenge.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerCustom {

    @ExceptionHandler({GenericNotFountException.class})
    public ResponseEntity<Object> handleException(GenericNotFountException ex) {

        ApiError apiError = ApiError.builder()
                .message(ex.getErrorCode().getMessage())
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), NOT_FOUND);
    }
}
