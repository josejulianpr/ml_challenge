package com.ml.challenge.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApiErrorTest {

    @Test
    void createOneError_whenDataIsOk_returnIt() {

        final String message = "aCustomMessage";

        ApiError error = ApiError.builder()
                .message(message)
                .build();

        assertNotNull(error);
        assertEquals(message, error.getMessage());
    }
}