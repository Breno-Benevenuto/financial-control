package com.brenobenevenuto.financialcontrol.handlers;

import org.springframework.security.access.AccessDeniedException;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brenobenevenuto.financialcontrol.domain.Response.ApiExceptionResponse;
import com.brenobenevenuto.financialcontrol.domain.enums.ExceptionCodeMappingEnum;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiExceptionResponse> handleRuntimeEx(RuntimeException exception){
        int httpCode = ExceptionCodeMappingEnum.getHttpCodeForEx(exception);

        ApiExceptionResponse response = new ApiExceptionResponse(
            httpCode,
            exception.getMessage(),
            Collections.EMPTY_LIST
        );
        log.error("[ERROR] Message : {0}, StatusCode : {1}, Trace : {2}", exception.getMessage(), httpCode , exception.getStackTrace());
        return new ResponseEntity<>(response, HttpStatus.valueOf(httpCode));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiExceptionResponse> handleAccessDeniedEx(AccessDeniedException exception){
        ApiExceptionResponse response = new ApiExceptionResponse(
            HttpStatus.FORBIDDEN.value(),
            exception.getMessage(),
            Collections.EMPTY_LIST
        );
        log.error("[ERROR] Message : {0}, StatusCode : {1}, Trace : {2}", exception.getMessage(), HttpStatus.FORBIDDEN , exception.getStackTrace());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
