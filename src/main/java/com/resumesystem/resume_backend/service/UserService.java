package com.resumesystem.resume_backend.service;

import com.resumesystem.resume_backend.dto.UserRegisterRequest;
import com.resumesystem.resume_backend.dto.UserResponse;
import java.util.Optional;

public interface UserService {
    UserResponse registerUser(UserRegisterRequest request);
    Optional<UserResponse> getUserByEmail(String email);
    Optional<UserResponse> loginUser(String email, String password);
}
