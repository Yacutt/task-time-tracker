package ru.bmstu.task_time_tracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bmstu.task_time_tracker.mapper.EmployeeMapper;
import ru.bmstu.task_time_tracker.model.dto.CreateEmployeeDto;
import ru.bmstu.task_time_tracker.model.entity.EmployeeEntity;
import ru.bmstu.task_time_tracker.service.impl.EmployeeServiceImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock private EmployeeMapper employeeMapper;
    @InjectMocks private EmployeeServiceImpl employeeService;

    @Test
    void createEmployee_shouldGenerateIdAndPersist() {
        CreateEmployeeDto dto = new CreateEmployeeDto();
        dto.setFullName("Иванов Иван Иванович");
        dto.setEmail("ivanov@example.com");

        EmployeeEntity result = employeeService.createEmployee(dto);

        assertNotNull(result.getId());
        assertEquals("Иванов Иван Иванович", result.getFullName());
        assertEquals("ivanov@example.com", result.getEmail());
        assertNotNull(result.getCreatedAt());
        verify(employeeMapper, times(1)).insert(any(EmployeeEntity.class));
    }

    @Test
    void getEmployeeById_whenExists_shouldReturnEntity() {
        UUID employeeId = UUID.randomUUID();
        EmployeeEntity expected = EmployeeEntity.builder()
                .id(employeeId)
                .fullName("Петров Петр Петрович")
                .email("petrov@example.com")
                .build();

        when(employeeMapper.findById(employeeId)).thenReturn(expected);

        EmployeeEntity result = employeeService.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());
        assertEquals("Петров Петр Петрович", result.getFullName());
        verify(employeeMapper, times(1)).findById(employeeId);
    }

    @Test
    void getEmployeeById_whenNotFound_shouldThrowException() {
        UUID fakeId = UUID.randomUUID();
        when(employeeMapper.findById(fakeId)).thenReturn(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.getEmployeeById(fakeId)
        );
        assertTrue(ex.getMessage().contains("не найден"));
        verify(employeeMapper, times(1)).findById(fakeId);
    }
}