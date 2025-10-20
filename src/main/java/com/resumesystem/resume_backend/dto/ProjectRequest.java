package com.resumesystem.resume_backend.dto;

import lombok.Data;

@Data
public class ProjectRequest {
    private Long resume_id;
    private String title;
    private String description;
    private String project_link;
    private String technologies_used;
}
