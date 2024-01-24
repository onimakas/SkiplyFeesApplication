package com.skiply.fees_collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WebClientStudentRetrieveException extends RuntimeException{
    public WebClientStudentRetrieveException(String message,Exception exception) {
        super(message,exception);}
}

