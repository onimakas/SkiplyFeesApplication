package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CurrencyCodeMismatchException extends RuntimeException {

    public CurrencyCodeMismatchException(String message) {
        super(message);
    }

}