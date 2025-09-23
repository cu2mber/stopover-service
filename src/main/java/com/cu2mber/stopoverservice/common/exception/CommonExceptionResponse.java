package com.cu2mber.stopoverservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonExceptionResponse {

    private final int statusCode;

    private final String message;

    private final String uri;

}
