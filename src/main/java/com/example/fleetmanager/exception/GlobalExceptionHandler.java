package com.example.fleetmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.fleetmanager.exception.model.ErrorResponse;
import com.example.fleetmanager.exception.model.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja específicamente nuestra excepción de "No encontrado"
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), 
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
    // Tomamos el primer mensaje de error que configuramos en el @Min
    String message = ex.getConstraintViolations().iterator().next().getMessage();
    
    ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message
    );
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}

    // Maneja cualquier otro error genérico (Error 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                "Ocurrió un error interno en el servidor"
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}

