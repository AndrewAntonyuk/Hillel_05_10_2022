package com.hillel.online_shop.exception.exeptionhandler;

import com.hillel.online_shop.exception.ProductNotFoundException;
import com.hillel.online_shop.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class RuntimeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class, UserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception e, WebRequest request) {
        String bodyOfResponse = e.getMessage();

        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    protected ResponseEntity<Object> handleForbidden(Exception e, WebRequest request) {
        String bodyOfResponse = e.getMessage();

        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.FORBIDDEN,
                request);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleBadRequest(Exception e, WebRequest request) {
        String bodyOfResponse = e.getMessage();

        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }


}