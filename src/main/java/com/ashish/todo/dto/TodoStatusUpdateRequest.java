package com.ashish.todo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoStatusUpdateRequest {
    private int projectId;
    private int todoId;
    private String status;
}
