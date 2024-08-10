package com.ashish.todo.service.user;

import com.ashish.todo.dto.ProjectCreationRequest;
import com.ashish.todo.model.Project;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<Project> saveProject(ProjectCreationRequest request);
}
