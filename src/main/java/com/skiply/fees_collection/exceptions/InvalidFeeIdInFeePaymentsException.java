package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidFeeIdInFeePaymentsException extends RuntimeException {

    public InvalidFeeIdInFeePaymentsException(String message) {
        super(message);
    }

}