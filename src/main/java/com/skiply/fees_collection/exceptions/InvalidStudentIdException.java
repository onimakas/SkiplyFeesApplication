package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidStudentIdException extends RuntimeException {

    public InvalidStudentIdException(String message) {
        super(message);
    }

}