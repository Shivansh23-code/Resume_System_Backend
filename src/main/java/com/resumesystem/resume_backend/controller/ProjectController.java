package com.resumesystem.resume_backend.controller;

import com.resumesystem.resume_backend.dto.ProjectRequest;
import com.resumesystem.resume_backend.model.Project;
import com.resumesystem.resume_backend.model.Resume;
import com.resumesystem.resume_backend.repository.ProjectRepository;
import com.resumesystem.resume_backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest request) {
        Resume resume = resumeRepository.findById(request.getResume_id()).orElse(null);
        if (resume == null) {
            return ResponseEntity.badRequest().body("Resume not found for id: " + request.getResume_id());
        }

        Project project = new Project();
        project.setResume(resume);
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setProjectLink(request.getProject_link());
        project.setTechnologiesUsed(request.getTechnologies_used());

        Project savedProject = projectRepository.save(project);
        return ResponseEntity.status(201).body(savedProject);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setTitle(projectDetails.getTitle());
                    project.setDescription(projectDetails.getDescription());
                    project.setProjectLink(projectDetails.getProjectLink());
                    project.setTechnologiesUsed(projectDetails.getTechnologiesUsed());
                    return ResponseEntity.ok(projectRepository.save(project));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    projectRepository.delete(project);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
