package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@RestControllerAdvice
public class FeesTransactionExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex) {
        return status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDetails(LocalDateTime.now(), ex.getMessage(), null));
    }

    @ExceptionHandler(FeesNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleFeesNotFoundException(FeesNotFoundException ex) {
        return status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(LocalDateTime.now(), ex.getMessage(), null));
    }

    @ExceptionHandler(FeesPaymentNotFoundException.class)
    public ResponseEntity<String> handleFeesPaymentNotFoundException(FeesPaymentNotFoundException ex) {
        return status(HttpStatus.NOT_FOUND).body(ex.getMessage());


    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleStudentException(TransactionNotFoundException ex) {
        return status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException ex) {
        return status(HttpStatus.BAD_REQUEST).body(getValidationErrors(ex.getBindingResult()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<String>> handleBindException(BindException ex, WebRequest request) {
        return status(HttpStatus.BAD_REQUEST).body(getValidationErrors(ex.getBindingResult()));
    }

    private List<String> getValidationErrors(BindingResult bindingResult) {
        List<String> validationErrors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            validationErrors.add(errorMessage);
        }

        return validationErrors;
    }

}
