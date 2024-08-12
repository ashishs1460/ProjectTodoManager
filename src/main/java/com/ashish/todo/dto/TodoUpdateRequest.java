package com.ashish.todo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequest {
    private int projectId;
    private int todoId;
    private String updatedDescription;
}
