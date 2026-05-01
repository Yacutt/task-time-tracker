package ru.bmstu.task_time_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.task_time_tracker.mapper.TaskMapper;
import ru.bmstu.task_time_tracker.model.dto.CreateTaskDto;
import ru.bmstu.task_time_tracker.model.dto.UpdateTaskStatusDto;
import ru.bmstu.task_time_tracker.model.entity.TaskEntity;
import ru.bmstu.task_time_tracker.service.TaskService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    @Override
    public TaskEntity createTask(CreateTaskDto dto) {
        TaskEntity task = TaskEntity.builder()
                .id(UUID.randomUUID())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status("NEW")
                .createdAt(LocalDateTime.now())
                .build();
        taskMapper.insert(task);
        return task;
    }

    @Override
    public TaskEntity getTaskById(UUID id) {
        TaskEntity task = taskMapper.findById(id);
        if (task == null) {
            throw new IllegalArgumentException("Задача с ID " + id + " не найдена");
        }
        return task;
    }

    @Override
    public TaskEntity updateStatus(UUID id, UpdateTaskStatusDto dto) {
        TaskEntity existing = getTaskById(id);

        existing.setStatus(dto.getStatus());
        taskMapper.updateStatus(id, dto.getStatus());

        return existing;
    }
}