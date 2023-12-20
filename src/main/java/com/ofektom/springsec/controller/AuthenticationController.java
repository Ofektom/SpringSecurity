package com.ofektom.springsec.controller;

import com.ofektom.springsec.dto.SignUpRequest;
import com.ofektom.springsec.entities.Users;
import com.ofektom.springsec.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<Users> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(userService.signup.apply(signUpRequest));
    }
}
