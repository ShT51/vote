package com.topjava.vote.controller;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.response.ErrorResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, prepareErrorFieldResponse(ex.getBindingResult()), prepareDefaultHeaders(), status, request);
    }
    
    @ExceptionHandler(VoteException.class)
    protected ResponseEntity<Object> handleVoteExceptions(VoteException ex, WebRequest request) {
        return handleExceptionInternal(ex, ErrorResponseData.of(ex.getMessage()), prepareDefaultHeaders(), ex.getStatus(), request);
    }
    
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        log.error("Unhandled exception occurred. Message: '{}', Cause: '{}'", ex.getMessage(), ex.getCause());
        return handleExceptionInternal(
                ex, ErrorResponseData.of(VoteException.DEFAULT_ERROR_MESSAGE),
                prepareDefaultHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    private ErrorResponseData<Object> prepareErrorFieldResponse(BindingResult bindingResult) {
        var errors = bindingResult.getFieldErrors()
                                  .stream()
                                  .map(error -> new ErrorResponseData.ErrorField(error.getField(), error.getDefaultMessage()))
                                  .toList();
        
        return ErrorResponseData.of(errors);
    }
    
    private HttpHeaders prepareDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
