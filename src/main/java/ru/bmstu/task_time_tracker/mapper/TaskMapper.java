package ru.bmstu.task_time_tracker.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler;
import ru.bmstu.task_time_tracker.model.entity.TaskEntity;

import java.util.UUID;

@Mapper
@Repository
public interface TaskMapper {

    @Insert("INSERT INTO task (id, title, description, status) VALUES " +
            "(#{id, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}, " +
            "#{title}, #{description}, #{status})")
    void insert(TaskEntity task);

    @Select("SELECT id, title, description, status, created_at FROM task " +
            "WHERE id = #{id, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}")
    @Results({
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class)
    })
    TaskEntity findById(@Param("id") UUID id);

    @Update("UPDATE task SET status = #{status} " +
            "WHERE id = #{id, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}")
    void updateStatus(@Param("id") UUID id, @Param("status") String status);
}