package ru.bmstu.task_time_tracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bmstu.task_time_tracker.mapper.TaskMapper;
import ru.bmstu.task_time_tracker.model.dto.CreateTaskDto;
import ru.bmstu.task_time_tracker.model.entity.TaskEntity;
import ru.bmstu.task_time_tracker.service.impl.TaskServiceImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock private TaskMapper taskMapper;
    @InjectMocks private TaskServiceImpl taskService;

    @Test
    void createTask_shouldSetDefaultStatusAndReturnEntity() {
        CreateTaskDto dto = new CreateTaskDto();
        dto.setTitle("Test Task");
        dto.setDescription("Description");

        TaskEntity result = taskService.createTask(dto);

        assertNotNull(result.getId());
        assertEquals("NEW", result.getStatus());
        assertEquals("Test Task", result.getTitle());
        verify(taskMapper, times(1)).insert(any(TaskEntity.class));
    }

    @Test
    void getTaskById_whenNotFound_shouldThrowException() {
        UUID fakeId = UUID.randomUUID();
        when(taskMapper.findById(fakeId)).thenReturn(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.getTaskById(fakeId)
        );
        assertTrue(ex.getMessage().contains("не найдена"));
    }
}