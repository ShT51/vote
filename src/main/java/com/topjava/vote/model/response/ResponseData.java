package com.topjava.vote.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    public static final String SUCCESS = "success";
    
//    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;
    private String status;
    private Instant actualTimestamp;

    public static <T> ResponseData<T> of(T data) {
        return new ResponseData<>(data, SUCCESS, Instant.now());
    }
    
    public static ResponseData<Object> ok() {
        return new ResponseData<>(null, SUCCESS, Instant.now());
    }
}
