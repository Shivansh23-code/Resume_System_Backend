package com.resumesystem.resume_backend.service;

import com.resumesystem.resume_backend.model.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);
    List<Project> getAllProjects();
    Optional<Project> getProjectById(Long id);
    Project updateProject(Long id, Project projectDetails);
    void deleteProject(Long id);
}