package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FeesNotFoundException extends RuntimeException {
    public FeesNotFoundException(String message) {
        super(message);
    }
}