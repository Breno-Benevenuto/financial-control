package com.brenobenevenuto.financialcontrol.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brenobenevenuto.financialcontrol.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>HandleUserNotFound(Exception exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>GenericException(Exception exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
