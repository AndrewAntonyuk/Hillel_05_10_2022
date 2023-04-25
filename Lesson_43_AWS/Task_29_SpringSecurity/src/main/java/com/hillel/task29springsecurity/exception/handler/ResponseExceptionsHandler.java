package com.hillel.task29springsecurity.exception.handler;

import com.hillel.task29springsecurity.exception.NoSuchProductException;
import com.hillel.task29springsecurity.exception.NoSuchUserException;
import com.hillel.task29springsecurity.exception.UserAlreadyExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class ResponseExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchUserException.class, NoSuchProductException.class})
    protected ResponseEntity<Object> notFoundHandler(RuntimeException e, WebRequest request) {
        String bodyOfResponse = e.getMessage();

        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    protected ResponseEntity<Object> alreadyExistHandler(RuntimeException e, WebRequest request) {
        String bodyOfResponse = e.getMessage();

        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE,
                request
        );
    }
}
