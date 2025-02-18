package io.bytesbank.registration.controller;

import io.bytesbank.registration.dto.ErrorInfo;
import io.bytesbank.registration.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class MyControllerAdvisor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleAnyOtherException(Exception e) {
        ErrorInfo info = ErrorInfo.builder()
                .message(e.getMessage())
                .errorCode(500)
                .time(new Date())
                .build();
        return new ResponseEntity<>(info, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleUserNotFound(UserNotFoundException e) {
        ErrorInfo info = ErrorInfo.builder()
                .message(e.getMessage())
                .errorCode(404)
                .time(new Date())
                .build();
        return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
    }

}
