package ru.bmstu.task_time_tracker.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler;
import ru.bmstu.task_time_tracker.model.entity.EmployeeEntity;
import java.util.UUID;

@Mapper
@Repository
public interface EmployeeMapper {

    @Insert("INSERT INTO employee (id, full_name, email) VALUES " +
            "(#{id, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}, #{fullName}, #{email})")
    void insert(EmployeeEntity employee);

    @Select("SELECT id, full_name, email, created_at FROM employee WHERE id = #{id, jdbcType=OTHER, typeHandler=ru.bmstu.task_time_tracker.config.typehandler.UuidTypeHandler}")
    @Results({
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class)
    })
    EmployeeEntity findById(@Param("id") UUID id);
}