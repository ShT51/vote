package com.topjava.vote.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseData<T> {
    
    public static final String ERROR = "error";
    
    private T errorData;
    private String status;
    private Instant actualTimestamp;
    
    public static <T> ErrorResponseData<T> of(T errorData) {
        return new ErrorResponseData<>(errorData, ERROR, Instant.now());
    }
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorField {
        private String field;
        private String message;
    }
}
