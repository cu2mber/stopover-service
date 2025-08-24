package com.oeso.stopoverservice.common.exception;

public class BadRequestException extends CommonHttpException{
    private static final int STATUS_CODE = 401;

    public BadRequestException() {
        super(STATUS_CODE, "잘못된 요청 입니다.");
    }
}
