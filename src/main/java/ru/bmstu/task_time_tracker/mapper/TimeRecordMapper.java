package ru.bmstu.task_time_tracker.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler;
import ru.bmstu.task_time_tracker.model.entity.TimeRecordEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface TimeRecordMapper {
    @Insert("INSERT INTO time_record (id, employee_id, task_id, start_time, end_time, description) VALUES " +
            "(#{id, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}, " +
            "#{employeeId, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}, " +
            "#{taskId, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}, " +
            "#{startTime}, #{endTime}, #{description})")
    void insert(TimeRecordEntity record);

    @Select("SELECT * FROM time_record WHERE employee_id = #{employeeId, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler} " +
            "AND start_time >= #{start} AND end_time <= #{end}")
    @Results({
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "employeeId", column = "employee_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "taskId", column = "task_id", typeHandler = UuidTypeHandler.class)
    })
    List<TimeRecordEntity> findByPeriod(@Param("employeeId") UUID employeeId,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);
}