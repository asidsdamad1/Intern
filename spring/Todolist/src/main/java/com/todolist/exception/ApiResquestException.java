package com.todolist.exception;

public class ApiResquestException extends RuntimeException {
    public ApiResquestException(String message) {
        super(message);
    }

    public ApiResquestException(String message, Throwable cause) {
        super(message, cause);
    }
}
