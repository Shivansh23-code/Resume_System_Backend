package com.resumesystem.resume_backend.controller;

import com.resumesystem.resume_backend.dto.ResumeUpdateRequest;
import com.resumesystem.resume_backend.model.Resume;
import com.resumesystem.resume_backend.repository.UserRepository;
import com.resumesystem.resume_backend.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getResumeByUserId(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return new ResponseEntity<>(Map.of("error", "User not found"), HttpStatus.NOT_FOUND);
        }
        Optional<Resume> resumeOpt = resumeService.getResumeByUserId(userId);
        if (resumeOpt.isEmpty()) {
            return new ResponseEntity<>(Map.of("error", "Resume not found for user"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(resumeOpt.get());
    }

    @PutMapping("/{resumeId}")
    public ResponseEntity<?> updateResume(@PathVariable Long resumeId, @RequestBody ResumeUpdateRequest request) {
        Optional<Resume> updatedResumeOpt = resumeService.updateResume(resumeId, request);
        if (updatedResumeOpt.isEmpty()) {
            return new ResponseEntity<>(Map.of("error", "Resume not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedResumeOpt.get());
    }
}
