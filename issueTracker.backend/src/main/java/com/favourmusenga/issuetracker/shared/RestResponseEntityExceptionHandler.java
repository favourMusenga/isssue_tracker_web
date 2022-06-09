package com.favourmusenga.issuetracker.shared;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomErrorResponse;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handle not valid request body
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        CustomErrorResponse responseBody = new CustomErrorResponse<>(HttpStatus.BAD_REQUEST.value(),Arrays.asList(errors),HttpStatus.BAD_REQUEST.toString());

        return handleExceptionInternal(ex,responseBody,headers,HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Object> handleCustomNotFoundException(final CustomNotFoundException ex, final WebRequest request){
        CustomErrorResponse responseBody = new CustomErrorResponse<>(HttpStatus.NOT_FOUND.value(),ex.getMessage(),HttpStatus.NOT_FOUND.toString());

        return handleExceptionInternal(ex,responseBody,new HttpHeaders(),HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<Object> handleCustomBadRequestException(final CustomBadRequestException ex, final WebRequest request){
        CustomErrorResponse responseBody = new CustomErrorResponse<>(HttpStatus.NOT_FOUND.value(),ex.getMessage(),HttpStatus.NOT_FOUND.toString());

        return handleExceptionInternal(ex,responseBody,new HttpHeaders(),HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex, final WebRequest request){
        CustomErrorResponse responseBody = new CustomErrorResponse<>(HttpStatus.UNAUTHORIZED.value(),ex.getMessage(),HttpStatus.UNAUTHORIZED.toString());

        return handleExceptionInternal(ex,responseBody,new HttpHeaders(),HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAAuthenticationException(final AuthenticationException ex, final WebRequest request){
        CustomErrorResponse responseBody = new CustomErrorResponse<>(HttpStatus.UNAUTHORIZED.value(),"Invalid username or password",HttpStatus.UNAUTHORIZED.toString());

        return handleExceptionInternal(ex,responseBody,new HttpHeaders(),HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> NoSuchElementException(final NoSuchElementException ex, final WebRequest request){

        CustomErrorResponse responseBody = new CustomErrorResponse<>(HttpStatus.BAD_REQUEST.value(),"PLease ensure that the required id exists",HttpStatus.UNAUTHORIZED.toString());;
        return handleExceptionInternal(ex,responseBody,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
}
