package com.cu2mber.stopoverservice.common.exception;

import lombok.Getter;

@Getter
public class StopoverException extends BusinessException{
    private final StopoverErrorCode errorCode;

    public StopoverException(StopoverErrorCode errorCode, Object... args) {
        super(errorCode.getStatus().name(), errorCode.getMessage(), args);
        this.errorCode = errorCode;
    }
}
