package com.ashish.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectUpdateRequest {
    private int projectId;
    private String updatedName;
}
