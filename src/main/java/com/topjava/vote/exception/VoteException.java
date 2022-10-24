package com.topjava.vote.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class VoteException extends RuntimeException {
   
    public static final String DEFAULT_ERROR_MESSAGE = "Please, try again later";
    
    private final String message;
    private final HttpStatus status;
    
    public static VoteException exception() {
       return new VoteException(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public static VoteException exception(String message) {
        return new VoteException(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public static VoteException badRequest(String message) {
        return new VoteException(message, HttpStatus.BAD_REQUEST);
    }
    
}
