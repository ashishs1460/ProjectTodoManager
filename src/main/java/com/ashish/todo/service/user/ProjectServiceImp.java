package com.ashish.todo.service.user;

import com.ashish.todo.dto.ProjectCreationRequest;
import com.ashish.todo.model.Project;
import com.ashish.todo.model.User;
import com.ashish.todo.respository.ProjectRepository;
import com.ashish.todo.respository.TodoRepository;
import com.ashish.todo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
}
