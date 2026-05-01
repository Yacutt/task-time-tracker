package ru.bmstu.task_time_tracker.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TaskEntity {
    private UUID id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}