package com.topjava.vote.controller;

import com.topjava.vote.model.dto.UserDto;
import com.topjava.vote.model.response.ResponseData;
import com.topjava.vote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Object> registerUser(@RequestBody @Valid UserDto userDto) {
        userService.registerUser(userDto);
        return ResponseData.ok();
    }
}