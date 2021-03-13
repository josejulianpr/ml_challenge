package com.ml.challenge.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_ENOUGH_INFORMATION("No hay suficiente información"),
    SATELLITE_NOT_FOUND("Satellite not found");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
