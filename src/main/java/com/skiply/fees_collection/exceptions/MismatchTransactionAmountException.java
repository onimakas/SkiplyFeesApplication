package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MismatchTransactionAmountException extends RuntimeException {

    public MismatchTransactionAmountException(String message) {
        super(message);
    }

}