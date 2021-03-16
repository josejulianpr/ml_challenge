package com.ml.challenge.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.ml.challenge.enums.ErrorCode.SATELLITE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerCustomTest {

    @InjectMocks
    private ExceptionHandlerCustom handler;

    @Test
    void handleException() {
        GenericNotFountException ex = new GenericNotFountException(SATELLITE_NOT_FOUND);

        ApiError expectedError = ApiError.builder()
                .message(SATELLITE_NOT_FOUND.getMessage())
                .build();

        ResponseEntity<Object> res = handler.handleException(ex);

        assertEquals(NOT_FOUND, res.getStatusCode());
        assertEquals(expectedError, res.getBody());
    }
}