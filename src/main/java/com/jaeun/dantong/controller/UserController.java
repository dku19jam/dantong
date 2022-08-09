package com.jaeun.dantong.controller;

import com.jaeun.dantong.domain.dto.UserDto;
import com.jaeun.dantong.domain.dto.request.LoginRequest;
import com.jaeun.dantong.domain.dto.response.LoginResponse;
import com.jaeun.dantong.domain.dto.response.UserResponse;
import com.jaeun.dantong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<String> register(@RequestBody @Valid final UserDto userDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return ResponseEntity.created(uri).body(userService.singup(userDto));
    }


    @GetMapping("/users/confirm")
    public ResponseEntity<UserResponse> confirm(@RequestParam("token") final String token) {
        UserResponse userResponse = userService.confirmToken(token);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid final LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok().body(response);
    }
}
