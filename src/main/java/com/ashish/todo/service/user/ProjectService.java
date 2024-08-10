package com.ashish.todo.service.user;

import com.ashish.todo.dto.*;
import com.ashish.todo.model.Project;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<Project> saveProject(ProjectCreationRequest request);

    ResponseEntity<Project> findProjectById(int id);

    ResponseEntity<Project> updateProject(ProjectUpdateRequest request);

    ResponseEntity<Project> createTodo(TodoCreationRequest request);

    ResponseEntity<Project> deleteTodo(TodoDeletionRequest request);

    ResponseEntity<Project> updateTodo(TodoUpdateRequest request);
}
