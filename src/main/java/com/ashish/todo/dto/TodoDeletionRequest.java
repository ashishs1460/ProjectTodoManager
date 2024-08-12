package com.ashish.todo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDeletionRequest {
    private int projectId;
    private int todoId;
}
