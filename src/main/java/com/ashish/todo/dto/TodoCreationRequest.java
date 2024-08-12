package com.ashish.todo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreationRequest {
    private int projectId;
    private String description;
}
