package ru.bmstu.task_time_tracker.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateTaskStatusDto {
    @Pattern(regexp = "NEW|IN_PROGRESS|DONE", message = "Допустимые статусы: NEW, IN_PROGRESS, DONE")
    private String status;
}