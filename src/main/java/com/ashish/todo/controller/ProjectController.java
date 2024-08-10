package com.ashish.todo.controller;

import com.ashish.todo.dto.*;
import com.ashish.todo.model.Project;
import com.ashish.todo.service.user.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/saveProject")
    public ResponseEntity<Project> createProject(@RequestBody  ProjectCreationRequest request){
        return  projectService.saveProject(request);
    }

    @GetMapping("/findProject")
    public ResponseEntity<Project> findProjectById(@RequestParam("id") int id) {
        return projectService.findProjectById(id);
    }

    @PostMapping("/updateProject")
    public ResponseEntity<Project> updateProject(@RequestBody ProjectUpdateRequest request){
        return projectService.updateProject(request);
    }

    @PostMapping("/createTodo")
    public ResponseEntity<Project> createTodo(@RequestBody TodoCreationRequest request){
        return projectService.createTodo(request);
    }

    @PostMapping("/deleteTodo")
    public ResponseEntity<Project> deleteTodo(@RequestBody TodoDeletionRequest request){
        return projectService.deleteTodo(request);
    }

    @PostMapping("/updateTodo")
    public ResponseEntity<Project> updateTodo(@RequestBody TodoUpdateRequest request){

        return projectService.updateTodo(request);
    }

}
