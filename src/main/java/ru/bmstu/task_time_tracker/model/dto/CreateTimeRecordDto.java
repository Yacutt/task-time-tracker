package ru.bmstu.task_time_tracker.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateTimeRecordDto {
    @NotNull(message = "ID сотрудника обязателен")
    private UUID employeeId;

    @NotNull(message = "ID задачи обязателен")
    private UUID taskId;

    @NotNull(message = "Время начала обязательно")
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания обязательно")
    private LocalDateTime endTime;

    private String description;

    @AssertTrue(message = "Время окончания должно быть позже времени начала")
    public boolean isTimeRangeValid() {
        return startTime != null && endTime != null && endTime.isAfter(startTime);
    }
}