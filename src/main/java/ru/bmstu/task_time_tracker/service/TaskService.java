package ru.bmstu.task_time_tracker.service;

import ru.bmstu.task_time_tracker.model.dto.CreateTaskDto;
import ru.bmstu.task_time_tracker.model.dto.UpdateTaskStatusDto;
import ru.bmstu.task_time_tracker.model.entity.TaskEntity;

public interface TaskService {
    TaskEntity createTask(CreateTaskDto dto);
    TaskEntity getTaskById(java.util.UUID id);
    TaskEntity updateStatus(java.util.UUID id, UpdateTaskStatusDto dto);
}