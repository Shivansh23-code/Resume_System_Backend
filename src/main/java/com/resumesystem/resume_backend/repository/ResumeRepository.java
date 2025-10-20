package com.resumesystem.resume_backend.repository;

import com.resumesystem.resume_backend.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByUserId(Long userId);
}