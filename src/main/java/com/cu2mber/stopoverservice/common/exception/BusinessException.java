package com.cu2mber.stopoverservice.common.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException{
    protected String status;
    private final String message;

    public BusinessException(String message, String status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public BusinessException(String state, String message, Object... args) {
        super(formattingErrorMessage(message, args));
        this.status = state;
        this.message = message;
    }

    private static String formattingErrorMessage(String message, Object... objects) {
        return message.formatted(objects);
    }
}
