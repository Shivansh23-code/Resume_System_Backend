package com.resumesystem.resume_backend.service;

import com.resumesystem.resume_backend.model.Project;
import com.resumesystem.resume_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id).map(project -> {
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setProjectLink(projectDetails.getProjectLink());
            project.setTechnologiesUsed(projectDetails.getTechnologiesUsed());
            return projectRepository.save(project);
        }).orElse(null);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.findById(id).ifPresent(projectRepository::delete);
    }

}
