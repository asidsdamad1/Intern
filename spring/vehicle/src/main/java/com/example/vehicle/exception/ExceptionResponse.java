package com.example.vehicle.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private ZonedDateTime timestamp;
}
