package com.intuit.editor.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationException extends RuntimeException {
    private final String code;

    private final String message;

    private final HttpStatus httpStatus;

    public ApplicationException(String code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = null;
    }

    public ApplicationException(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
