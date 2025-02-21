package com.vincle.ahl.demo.infraestructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Global exception handler for handling exceptions across the whole application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException exceptions.
     *
     * @param ex the exception
     * @param request the web request
     * @return a ResponseEntity containing error details and an HTTP status code
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles RuntimeException exceptions.
     *
     * @param ex the exception
     * @param request the web request
     * @return a ResponseEntity containing error details and an HTTP status code
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleIllegalRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}

/**
 * Class representing error details.
 */
@Data
@Builder
@AllArgsConstructor (access = AccessLevel.PUBLIC)
class ErrorDetails {
    /**
     * The HTTP status code.
     */
    private int status;

    /**
     * The error message.
     */
    private String message;

    /**
     * Additional details about the error.
     */
    private String details;
}
