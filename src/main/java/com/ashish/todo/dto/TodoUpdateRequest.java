package com.ashish.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoUpdateRequest {
    private int projectId;
    private int todoId;
    private String updatedDescription;
}
