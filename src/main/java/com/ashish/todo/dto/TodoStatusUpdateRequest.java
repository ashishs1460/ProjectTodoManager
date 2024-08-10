package com.ashish.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoStatusUpdateRequest {
    private int projectId;
    private int todoId;
    private String status;
}
