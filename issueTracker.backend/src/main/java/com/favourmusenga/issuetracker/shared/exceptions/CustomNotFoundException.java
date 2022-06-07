package com.favourmusenga.issuetracker.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends Exception {
    public CustomNotFoundException(String msg){
        super(msg);
    }
}
