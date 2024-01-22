package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FeesExpiryException extends RuntimeException {

    public FeesExpiryException(String message) {
        super(message);
    }

}