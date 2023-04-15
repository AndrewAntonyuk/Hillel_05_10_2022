package com.hillel.task_26_springweb.handler;

import com.hillel.task_26_springweb.exception.NoSuchOrderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchOrderException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest request) {
        String bodyOfResponse = e.getMessage();

        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }
}
