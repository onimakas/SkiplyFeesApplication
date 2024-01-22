package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentGradeMismatchException extends RuntimeException {

    public StudentGradeMismatchException(String message) {
        super(message);
    }

}