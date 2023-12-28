package com.appointment.appointment.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandle {
	 
    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(AppointmentException ex, WebRequest request) {
        ApiResponse errorDetails = new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date(), null);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> globalExceptionHandler(Exception ex, WebRequest request) {
        ApiResponse errorDetails = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Date(), null);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

