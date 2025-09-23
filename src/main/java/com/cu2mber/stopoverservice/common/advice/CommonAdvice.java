package com.cu2mber.stopoverservice.common.advice;

import com.cu2mber.stopoverservice.common.exception.CommonExceptionResponse;
import com.cu2mber.stopoverservice.common.exception.StopoverErrorCode;
import com.cu2mber.stopoverservice.common.exception.StopoverException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonAdvice {

    @ExceptionHandler(StopoverException.class)
    public ResponseEntity<CommonExceptionResponse> businessExceptionHandler(StopoverException e, HttpServletRequest request) {
        StopoverErrorCode errorCode = e.getErrorCode();

        CommonExceptionResponse exceptionResponse = new CommonExceptionResponse(
                errorCode.getStatus().value(),
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(errorCode.getStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        CommonExceptionResponse exceptionResponse = new CommonExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CommonExceptionResponse> exceptionHandler(Throwable e, HttpServletRequest request) {
        CommonExceptionResponse exceptionResponse = new CommonExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}
