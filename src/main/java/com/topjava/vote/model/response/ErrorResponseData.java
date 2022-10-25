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
    
    public static final String DEFAULT_STATUS = "error";
    
    private T error;
    private String status;
    private Instant actualTimestamp;
    
    public static <T> ErrorResponseData<T> of(T errorData) {
        return new ErrorResponseData<>(errorData, DEFAULT_STATUS, Instant.now());
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
