package com.ashish.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoCreationRequest {
    private int projectId;
    private String description;
}
