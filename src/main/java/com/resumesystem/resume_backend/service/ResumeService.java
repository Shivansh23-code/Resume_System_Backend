package com.resumesystem.resume_backend.service;

import com.resumesystem.resume_backend.dto.ResumeUpdateRequest;
import com.resumesystem.resume_backend.model.Resume;
import java.util.Optional;

public interface ResumeService {
    Optional<Resume> getResumeByUserId(Long userId);
    Optional<Resume> updateResume(Long resumeId, ResumeUpdateRequest request);
}
