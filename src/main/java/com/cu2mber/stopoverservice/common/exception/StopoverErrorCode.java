package com.cu2mber.stopoverservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StopoverErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    STOPOVER_CONFLICT(HttpStatus.CONFLICT, "이미 존재하는 경유지입니다."),
    STOPOVER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 경유지입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"Stopover 서비스와 통신 중 오류가 발생했습니다.");
    private final HttpStatus status;
    private final String message;

}
