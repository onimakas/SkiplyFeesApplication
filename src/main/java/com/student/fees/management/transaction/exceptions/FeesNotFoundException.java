package com.student.fees.management.transaction.exceptions;

public class FeesNotFoundException extends RuntimeException {
    public FeesNotFoundException(String message) {
        super(message);
    }
}