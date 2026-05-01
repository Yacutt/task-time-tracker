package ru.bmstu.task_time_tracker.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TimeRecordEntity {
    private UUID id;
    private UUID employeeId;
    private UUID taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}