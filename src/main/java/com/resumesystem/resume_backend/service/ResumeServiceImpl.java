package com.resumesystem.resume_backend.service;

import com.resumesystem.resume_backend.dto.ResumeUpdateRequest;
import com.resumesystem.resume_backend.model.Resume;
import com.resumesystem.resume_backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public Optional<Resume> getResumeByUserId(Long userId) {
        return resumeRepository.findByUserId(userId);
    }

    @Override
    public Optional<Resume> updateResume(Long resumeId, ResumeUpdateRequest request) {
        return resumeRepository.findById(resumeId)
                .map(resume -> {
                    if (request.getProfessionalSummary() != null) {
                        resume.setProfessionalSummary(request.getProfessionalSummary());
                    }
                    return resumeRepository.save(resume);
                });
    }
}
