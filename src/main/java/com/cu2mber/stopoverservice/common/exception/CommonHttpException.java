package com.cu2mber.stopoverservice.common.exception;

public class CommonHttpException extends RuntimeException {

    private final int statusCode;

    public CommonHttpException(final int statusCode, final String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public CommonHttpException(final int statusCode, final String message, Throwable cause){
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
