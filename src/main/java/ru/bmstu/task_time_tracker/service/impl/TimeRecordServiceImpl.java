package ru.bmstu.task_time_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.task_time_tracker.mapper.TimeRecordMapper;
import ru.bmstu.task_time_tracker.model.dto.CreateTimeRecordDto;
import ru.bmstu.task_time_tracker.model.dto.TimeRecordFilterDto;
import ru.bmstu.task_time_tracker.model.entity.TimeRecordEntity;
import ru.bmstu.task_time_tracker.service.TimeRecordService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimeRecordServiceImpl implements TimeRecordService {
    private final TimeRecordMapper timeRecordMapper;

    @Override
    public TimeRecordEntity createRecord(CreateTimeRecordDto dto) {
        TimeRecordEntity record = TimeRecordEntity.builder()
                .id(UUID.randomUUID())
                .employeeId(dto.getEmployeeId())
                .taskId(dto.getTaskId())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .description(dto.getDescription())
                .build();
        timeRecordMapper.insert(record);
        return record;
    }

    @Override
    public List<TimeRecordEntity> getRecordsByPeriod(TimeRecordFilterDto filter) {
        LocalDateTime start = filter.getDateFrom() != null ? filter.getDateFrom().atStartOfDay() : LocalDateTime.MIN;
        LocalDateTime end = filter.getDateTo() != null ? filter.getDateTo().atTime(23, 59, 59) : LocalDateTime.MAX;
        return timeRecordMapper.findByPeriod(filter.getEmployeeId(), start, end);
    }
}