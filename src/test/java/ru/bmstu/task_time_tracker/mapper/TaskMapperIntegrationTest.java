package ru.bmstu.task_time_tracker.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.bmstu.task_time_tracker.model.entity.TaskEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class TaskMapperIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired private TaskMapper taskMapper;

    @Test
    void insertAndFindById_shouldWorkWithUuid() {
        TaskEntity task = TaskEntity.builder()
                .id(UUID.randomUUID())
                .title("Integration Test")
                .description("Testing MyBatis + TestContainers")
                .status("IN_PROGRESS")
                .createdAt(LocalDateTime.now())
                .build();

        taskMapper.insert(task);
        TaskEntity found = taskMapper.findById(task.getId());

        assertNotNull(found);
        assertEquals(task.getId(), found.getId());
        assertEquals("Integration Test", found.getTitle());
        assertEquals("IN_PROGRESS", found.getStatus());
    }
}