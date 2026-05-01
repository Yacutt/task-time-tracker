package ru.bmstu.task_time_tracker.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class EmployeeEntity {
    private UUID id;
    private String fullName;
    private String email;
    private LocalDateTime createdAt;
}