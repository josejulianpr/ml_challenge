package com.ml.challenge.exception;

import com.ml.challenge.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GenericNotFountException extends RuntimeException {
    private static final long serialVersionUID = -9196376048502280346L;
    private final ErrorCode errorCode;

    public GenericNotFountException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
