package com.resumesystem.resume_backend.service;

import com.resumesystem.resume_backend.dto.UserRegisterRequest;
import com.resumesystem.resume_backend.dto.UserResponse;
import com.resumesystem.resume_backend.model.Resume;
import com.resumesystem.resume_backend.model.User;
import com.resumesystem.resume_backend.repository.ResumeRepository;
import com.resumesystem.resume_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    @Transactional
    public UserResponse registerUser(UserRegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        User newUser = userRepository.save(user);

        Resume newResume = new Resume();
        newResume.setUser(newUser);
        resumeRepository.save(newResume);

        newUser.getResumes().add(newResume);
        userRepository.save(newUser);

        UserResponse response = new UserResponse();
        response.setId(newUser.getId());
        response.setEmail(newUser.getEmail());
        response.setFirstName(newUser.getFirstName());
        response.setLastName(newUser.getLastName());
        return response;
    }

    @Override
    public Optional<UserResponse> loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setEmail(user.getEmail());
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    return response;
                });
    }
}
