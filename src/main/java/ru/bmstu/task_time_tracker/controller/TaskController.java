package ru.bmstu.task_time_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.task_time_tracker.model.dto.CreateTaskDto;
import ru.bmstu.task_time_tracker.model.dto.UpdateTaskStatusDto;
import ru.bmstu.task_time_tracker.model.entity.TaskEntity;
import ru.bmstu.task_time_tracker.service.TaskService;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskEntity> createTask(@Valid @RequestBody CreateTaskDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskEntity> updateStatus(@PathVariable UUID id, @Valid @RequestBody UpdateTaskStatusDto dto) {
        return ResponseEntity.ok(taskService.updateStatus(id, dto));
    }
}