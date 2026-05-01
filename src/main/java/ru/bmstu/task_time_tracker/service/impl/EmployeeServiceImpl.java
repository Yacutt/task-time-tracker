package ru.bmstu.task_time_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.task_time_tracker.mapper.EmployeeMapper;
import ru.bmstu.task_time_tracker.model.dto.CreateEmployeeDto;
import ru.bmstu.task_time_tracker.model.entity.EmployeeEntity;
import ru.bmstu.task_time_tracker.service.EmployeeService;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeEntity createEmployee(CreateEmployeeDto dto) {
        EmployeeEntity employee = EmployeeEntity.builder()
                .id(UUID.randomUUID())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .createdAt(LocalDateTime.now())
                .build();
        employeeMapper.insert(employee);
        return employee;
    }

    @Override
    public EmployeeEntity getEmployeeById(UUID id) {
        EmployeeEntity emp = employeeMapper.findById(id);
        if (emp == null) throw new IllegalArgumentException("Сотрудник с ID " + id + " не найден");
        return emp;
    }
}