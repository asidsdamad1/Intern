package com.todolist.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private ZonedDateTime timestamp;

    public ExceptionResponse(String message,
                             HttpStatus httpStatus,
                             ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
