package com.oeso.stopoverservice.common.exception;

public class NotFoundException extends CommonHttpException{

    private static final int STATUS_CODE = 404;

    public NotFoundException() {
        super(STATUS_CODE, "경유지를 찾을 수 없습니다.");
    }
}
