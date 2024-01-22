package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SchoolNotFoundException extends RuntimeException{
    public SchoolNotFoundException(String message) {
            super(message);}
}

