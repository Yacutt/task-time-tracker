package ru.bmstu.task_time_tracker.service;

import ru.bmstu.task_time_tracker.model.dto.CreateTimeRecordDto;
import ru.bmstu.task_time_tracker.model.dto.TimeRecordFilterDto;
import ru.bmstu.task_time_tracker.model.entity.TimeRecordEntity;
import java.util.List;

public interface TimeRecordService {
    TimeRecordEntity createRecord(CreateTimeRecordDto dto);
    List<TimeRecordEntity> getRecordsByPeriod(TimeRecordFilterDto filter);
}