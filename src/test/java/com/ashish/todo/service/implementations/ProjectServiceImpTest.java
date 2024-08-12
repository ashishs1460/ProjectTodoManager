package com.ashish.todo.service.implementations;

import com.ashish.todo.dto.*;
import com.ashish.todo.model.Project;
import com.ashish.todo.model.Todo;
import com.ashish.todo.model.User;
import com.ashish.todo.respository.ProjectRepository;
import com.ashish.todo.respository.TodoRepository;
import com.ashish.todo.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImpTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectServiceImp projectService;

    private User user;
    private Project project;
    private Todo todo;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1)
                .firstName("Ashish")
                .lastName("Sharma")
                .email("ashish@example.com")
                .password("password123")
                .build();

        project = Project.builder()
                .id(1)
                .title("Test Project")
                .user(user)
                .createdDate(LocalDate.now())
                .todos(new ArrayList<>())
                .build();

        todo = Todo.builder()
                .id(1)
                .description("Test Todo")
                .status("PENDING")
                .project(project)
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .build();
    }


    @Test
    public void testSaveProject_Success() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectCreationRequest request = new ProjectCreationRequest(1, "Test Project");
        ResponseEntity<Project> response = projectService.saveProject(request);

        assertNotNull(response.getBody());
        assertEquals("Test Project", response.getBody().getTitle());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    public void testSaveProject_UserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        ProjectCreationRequest request = new ProjectCreationRequest(1, "Test Project");
        ResponseEntity<Project> response = projectService.saveProject(request);

        assertTrue(response.getStatusCode().is4xxClientError());
        verify(projectRepository, times(0)).save(any(Project.class));
    }

    @Test
    public void testFindProjectById_Success() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));

        ResponseEntity<Project> response = projectService.findProjectById(1);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        verify(projectRepository, times(1)).findById(1);
    }

    @Test
    public void testFindProjectById_NotFound() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Project> response = projectService.findProjectById(1);

        assertTrue(response.getStatusCode().is4xxClientError());
        verify(projectRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateProject_Success() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectUpdateRequest request = new ProjectUpdateRequest(1, "Updated Project");
        ResponseEntity<Project> response = projectService.updateProject(request);

        assertNotNull(response.getBody());
        assertEquals("Updated Project", response.getBody().getTitle());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    public void testUpdateProject_NotFound() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        ProjectUpdateRequest request = new ProjectUpdateRequest(1, "Updated Project");
        ResponseEntity<Project> response = projectService.updateProject(request);

        assertTrue(response.getStatusCode().is4xxClientError());
        verify(projectRepository, times(0)).save(any(Project.class));
    }

    @Test
    public void testCreateTodo_Success() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        TodoCreationRequest request = new TodoCreationRequest(1, "New Todo");
        ResponseEntity<Project> response = projectService.createTodo(request);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTodos().size());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    public void testCreateTodo_ProjectNotFound() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        TodoCreationRequest request = new TodoCreationRequest(1, "New Todo");
        ResponseEntity<Project> response = projectService.createTodo(request);

        assertTrue(response.getStatusCode().is4xxClientError());
        verify(todoRepository, times(0)).save(any(Todo.class));
    }

    @Test
    public void testDeleteTodo_Success() {
        project.getTodos().add(todo);
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        TodoDeletionRequest request = new TodoDeletionRequest(1, 1);
        ResponseEntity<Project> response = projectService.deleteTodo(request);

        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().getTodos().size());
        verify(todoRepository, times(1)).delete(any(Todo.class));
    }

    @Test
    public void testDeleteTodo_NotFound() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        TodoDeletionRequest request = new TodoDeletionRequest(1, 1);
        ResponseEntity<Project> response = projectService.deleteTodo(request);

        assertTrue(response.getStatusCode().is4xxClientError());
        verify(todoRepository, times(0)).delete(any(Todo.class));
    }

    @Test
    public void testUpdateTodo_Success() {
        project.getTodos().add(todo);
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        TodoUpdateRequest request = new TodoUpdateRequest(1, 1, "Updated Description");
        ResponseEntity<Project> response = projectService.updateTodo(request);

        assertNotNull(response.getBody());
        assertEquals("Updated Description", todo.getDescription());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    public void testUpdateTodo_NotFound() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        TodoUpdateRequest request = new TodoUpdateRequest(1, 1, "Updated Description");
        ResponseEntity<Project> response = projectService.updateTodo(request);

        assertTrue(response.getStatusCode().is4xxClientError());
        verify(todoRepository, times(0)).save(any(Todo.class));
    }

    @Test
    public void testUpdateTodoStatus_Success() {
        project.getTodos().add(todo);
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        TodoStatusUpdateRequest request = new TodoStatusUpdateRequest(1, 1, "COMPLETED");
        ResponseEntity<Project> response = projectService.updateTodoStatus(request);

        assertNotNull(response.getBody());
        assertEquals("COMPLETED", todo.getStatus());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    public void testUpdateTodoStatus_NotFound() {
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        TodoStatusUpdateRequest request = new TodoStatusUpdateRequest(1, 1, "COMPLETED");
        ResponseEntity<Project> response = projectService.updateTodoStatus(request);

        assertTrue(response.getStatusCode().is4xxClientError());
        verify(todoRepository, times(0)).save(any(Todo.class));
    }
}
