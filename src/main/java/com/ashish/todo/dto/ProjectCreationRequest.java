package com.ashish.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreationRequest {
    @NotEmpty(message = "userId is mandatory")
    @NotNull(message = "userId is mandatory")
    private int userId;
    @NotEmpty(message = "projectName is mandatory")
    @NotNull(message = "projectName is mandatory")
    private String projectName;
}
