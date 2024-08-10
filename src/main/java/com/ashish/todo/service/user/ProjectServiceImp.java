package com.ashish.todo.service.user;

import com.ashish.todo.dto.*;
import com.ashish.todo.model.Project;
import com.ashish.todo.model.Todo;
import com.ashish.todo.model.User;
import com.ashish.todo.respository.ProjectRepository;
import com.ashish.todo.respository.TodoRepository;
import com.ashish.todo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService{

    private ProjectRepository projectRepository;
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    @Autowired
    public ProjectServiceImp(ProjectRepository projectRepository,
                                TodoRepository todoRepository,
                                UserRepository userRepository){
        this.projectRepository = projectRepository;
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Project> saveProject(ProjectCreationRequest request) {
        int userId = request.getUserId();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            Project project = Project.builder()
                    .user(user)
                    .title(request.getProjectName())
                    .createdDate(LocalDate.now())
                    .build();
            projectRepository.save(project);
            return  ResponseEntity.ok(project);

        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Project> findProjectById(int id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()){
            Project project = optionalProject.get();
            return  ResponseEntity.ok(project);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<Project> updateProject(ProjectUpdateRequest request) {
        Optional<Project> optionalProject = projectRepository.findById(request.getProjectId());

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            if (request.getUpdatedName() != null && !request.getUpdatedName().trim().isEmpty()) {
                project.setTitle(request.getUpdatedName());
                Project updatedProject = projectRepository.save(project);
                return ResponseEntity.ok(updatedProject);
            } else {

                return ResponseEntity.badRequest().body(null);
            }
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Project> createTodo(TodoCreationRequest request) {
        Optional<Project> optionalProject = projectRepository.findById(request.getProjectId());
        if(optionalProject.isPresent()){
            Project project = optionalProject.get();
            Todo todo = Todo.builder()
                    .project(project)
                    .description(request.getDescription())
                    .status("PENDING")
                    .createdDate(LocalDate.now())
                    .updatedDate(LocalDate.now())
                    .build();
            todoRepository.save(todo);
            project.getTodos().add(todo);
            Project updatedProject = projectRepository.save(project);
            return ResponseEntity.ok(updatedProject);

        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<Project> deleteTodo(TodoDeletionRequest request) {
        Optional<Project> optionalProject = projectRepository.findById(request.getProjectId());

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            List<Todo> todos = project.getTodos();

            Optional<Todo> todoToDelete = todos.stream()
                    .filter(todo -> todo.getId() == request.getTodoId())
                    .findFirst();

            if (todoToDelete.isPresent()) {
                todos.remove(todoToDelete.get());

                Project updatedProject = projectRepository.save(project);
                todoRepository.delete(todoToDelete.get());
                return ResponseEntity.ok(updatedProject);
            } else {

                return ResponseEntity.notFound().build();
            }
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Project> updateTodo(TodoUpdateRequest request) {
        Optional<Project> optionalProject = projectRepository.findById(request.getProjectId());

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            Optional<Todo> optionalTodo = project.getTodos().stream()
                    .filter(todo -> todo.getId() == request.getTodoId())
                    .findFirst();

            if (optionalTodo.isPresent()) {
                Todo todo = optionalTodo.get();

                todo.setDescription(request.getUpdatedDescription());
                todo.setUpdatedDate(LocalDate.now());

                todoRepository.save(todo);
                projectRepository.save(project);

                return ResponseEntity.ok(project);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
