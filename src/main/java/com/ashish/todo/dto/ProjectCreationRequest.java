package com.ashish.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectCreationRequest {
    @NotEmpty(message = "userId is mandatory")
    @NotNull(message = "userId is mandatory")
    private int userId;
    @NotEmpty(message = "projectName is mandatory")
    @NotNull(message = "projectName is mandatory")
    private String projectName;
}
