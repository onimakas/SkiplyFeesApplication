package com.skiply.fees_collection.exceptions;

public class FeesNotFoundException extends RuntimeException {
    public FeesNotFoundException(String message) {
        super(message);
    }
}