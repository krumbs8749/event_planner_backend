package com.app.dto;

import com.app.model.TaskPriority;
import com.app.model.TaskStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private Double cost;
    @JsonProperty
    private TaskStatus status;
    @JsonProperty
    private LocalDateTime deadline;
    @JsonProperty
    private TaskPriority priority;
    @JsonProperty
    private String assignee;
}
