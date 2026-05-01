package ru.bmstu.task_time_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.task_time_tracker.model.dto.CreateTimeRecordDto;
import ru.bmstu.task_time_tracker.model.dto.TimeRecordFilterDto;
import ru.bmstu.task_time_tracker.model.entity.TimeRecordEntity;
import ru.bmstu.task_time_tracker.service.TimeRecordService;
import java.util.List;

@RestController
@RequestMapping("/api/time-records")
@RequiredArgsConstructor
@Validated
public class TimeRecordController {
    private final TimeRecordService timeRecordService;

    @PostMapping
    public ResponseEntity<TimeRecordEntity> createRecord(@Valid @RequestBody CreateTimeRecordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeRecordService.createRecord(dto));
    }

    @GetMapping
    public ResponseEntity<List<TimeRecordEntity>> getRecordsByPeriod(@Valid TimeRecordFilterDto filter) {
        return ResponseEntity.ok(timeRecordService.getRecordsByPeriod(filter));
    }
}