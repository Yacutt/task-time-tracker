package ru.bmstu.task_time_tracker.model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class TimeRecordFilterDto {
    private UUID employeeId;
    private UUID taskId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}