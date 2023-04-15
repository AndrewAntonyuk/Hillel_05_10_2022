package com.hillel.task_26_springweb.exception;

public class NoSuchOrderException extends RuntimeException {

    public NoSuchOrderException(String message) {
        super(message);
    }
}
