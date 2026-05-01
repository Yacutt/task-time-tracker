package ru.bmstu.task_time_tracker.service;

import ru.bmstu.task_time_tracker.model.dto.CreateEmployeeDto;
import ru.bmstu.task_time_tracker.model.entity.EmployeeEntity;
import java.util.UUID;

public interface EmployeeService {
    EmployeeEntity createEmployee(CreateEmployeeDto dto);
    EmployeeEntity getEmployeeById(UUID id);
}