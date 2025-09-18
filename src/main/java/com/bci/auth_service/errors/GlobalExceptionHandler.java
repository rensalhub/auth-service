package com.bci.auth_service.errors;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

        String mensaje = ex.getBindingResult()
            .getAllErrors()
            .stream()
            .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));

        return new ResponseEntity<>(new ApiErrorResponse(mensaje), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException ex) {

        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExists(
        EmailAlreadyExistsException ex) {

        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
