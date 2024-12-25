package org.example.mapper;

import org.example.dto.TaskDto;
import org.example.entity.Task;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);
}
