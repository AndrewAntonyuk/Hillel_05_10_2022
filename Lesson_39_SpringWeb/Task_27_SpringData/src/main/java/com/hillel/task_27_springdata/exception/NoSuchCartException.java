package com.hillel.task_27_springdata.exception;

public class NoSuchCartException extends RuntimeException {
    public NoSuchCartException(String message) {
        super(message);
    }
}
