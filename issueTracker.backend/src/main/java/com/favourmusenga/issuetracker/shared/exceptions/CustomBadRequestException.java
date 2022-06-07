package com.favourmusenga.issuetracker.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends Exception{
    public CustomBadRequestException(String msg){
        super(msg);
    }
}
