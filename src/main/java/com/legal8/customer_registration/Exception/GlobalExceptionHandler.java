package com.legal8.customer_registration.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // handling specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Africa/Johannesburg")),
                        exception.getCause().toString(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handling specific exception
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Africa/Johannesburg")),
                        exception.getCause().toString(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handling global exception

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Africa/Johannesburg")),
                        "User registered", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Handling custom Validation error

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception){
        ErrorDetails errorDetails = new ErrorDetails(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Africa/Johannesburg")),
                "Validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
