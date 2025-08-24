package com.oeso.stopoverservice.common.exception;

public class ConflictException extends CommonHttpException {
    private static final int STATUS_CODE = 409;

    public ConflictException() {
        super(STATUS_CODE, "이미 있는 리소스 입니다.");
    }
}
