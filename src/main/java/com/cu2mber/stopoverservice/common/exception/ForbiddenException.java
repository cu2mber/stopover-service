package com.cu2mber.stopoverservice.common.exception;

public class ForbiddenException extends CommonHttpException{
    private static final int STATUS_CODE = 403;

    public ForbiddenException() {
        super(STATUS_CODE, "잘못된 접근 입니다.");
    }
}
