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
                .body(new ErrorDetails(ex.getMessage(), ex.toString()));
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCardNotFoundException(CardNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CurrencyCodeMismatchException.class)
    public ResponseEntity<ErrorDetails> handleCurrencyCodeMismatchException(CurrencyCodeMismatchException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidFeeIdInFeePaymentsException.class)
    public ResponseEntity<ErrorDetails> handleInvalidFeeIdInFeePaymentsException(InvalidFeeIdInFeePaymentsException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidStudentIdException.class)
    public ResponseEntity<ErrorDetails> handleInvalidStudentIdException(InvalidStudentIdException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MismatchTransactionAmountException.class)
    public ResponseEntity<ErrorDetails> handleMismatchTransactionAmountException(MismatchTransactionAmountException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(StudentGradeMismatchException.class)
    public ResponseEntity<ErrorDetails> handleStudentGradeMismatchException(StudentGradeMismatchException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(WebClientStudentRetrieveException.class)
    public ResponseEntity<ErrorDetails> handleWebClientStudentRetrieveException(WebClientStudentRetrieveException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SchoolNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleSchoolNotFoundException(SchoolNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidCardIdException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCardIdException(InvalidCardIdException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(FeesExpiryException.class)
    public ResponseEntity<ErrorDetails> handleFeesExpiryException(FeesExpiryException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(FeesNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleFeesNotFoundException(FeesNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(FeesPaymentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleFeesPaymentNotFoundException(FeesPaymentNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTransactionNotFoundException(TransactionNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(ex.getMessage(),ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
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
