package com.resumesystem.resume_backend.controller;

import com.resumesystem.resume_backend.dto.UserRegisterRequest;
import com.resumesystem.resume_backend.dto.UserResponse;
import com.resumesystem.resume_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest request) {
        // Check if email already exists
        if (userService.getUserByEmail(request.getEmail()).isPresent()) {
            return new ResponseEntity<>("Error: Email Already Exists!", HttpStatus.BAD_REQUEST);
        }

        UserResponse newUser = userService.registerUser(request);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRegisterRequest loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword())
                .map(user -> new ResponseEntity<>("Login Successful for user: " + user.getFirstName(), HttpStatus.OK))
                .orElse(new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED));
    }
}
