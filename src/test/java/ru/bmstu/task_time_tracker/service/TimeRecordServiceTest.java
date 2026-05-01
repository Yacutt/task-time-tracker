package ru.bmstu.task_time_tracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bmstu.task_time_tracker.mapper.TimeRecordMapper;
import ru.bmstu.task_time_tracker.model.dto.CreateTimeRecordDto;
import ru.bmstu.task_time_tracker.model.entity.TimeRecordEntity;
import ru.bmstu.task_time_tracker.service.impl.TimeRecordServiceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeRecordServiceTest {

    @Mock private TimeRecordMapper timeRecordMapper;
    @InjectMocks private TimeRecordServiceImpl timeRecordService;

    @Test
    void createRecord_shouldGenerateIdAndPersist() {
        CreateTimeRecordDto dto = new CreateTimeRecordDto();
        dto.setEmployeeId(UUID.randomUUID());
        dto.setTaskId(UUID.randomUUID());
        dto.setStartTime(LocalDateTime.now().minusHours(2));
        dto.setEndTime(LocalDateTime.now());
        dto.setDescription("Work");

        TimeRecordEntity result = timeRecordService.createRecord(dto);

        assertNotNull(result.getId());
        assertEquals(dto.getEmployeeId(), result.getEmployeeId());
        verify(timeRecordMapper, times(1)).insert(any(TimeRecordEntity.class));
    }

}