package ru.bmstu.task_time_tracker.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmployeeDto {
    @NotBlank(message = "ФИО сотрудника обязательно")
    private String fullName;

    @Email(message = "Некорректный формат email")
    @NotBlank(message = "Email обязателен")
    private String email;
}