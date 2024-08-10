package com.ashish.todo.controller;

import com.ashish.todo.dto.ProjectCreationRequest;
import com.ashish.todo.model.Project;
import com.ashish.todo.service.user.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        System.out.println(request.getUserId()+"UserId>>>>>>>>");
//        System.out.println(request.getProjectName()+"PRojectname>>>>>");
        return  projectService.saveProject(request);
    }
}
