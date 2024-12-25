package org.example.service;


import org.example.dto.TaskDto;
import org.example.entity.Task;
import org.example.exceptions.TaskNotFoundException;
import org.example.mapper.TaskMapper;
import org.example.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository,
                       TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDto getTaskByTitle(String title) {

        return taskMapper.toDto(taskRepository.findByTitle(title).orElseThrow(TaskNotFoundException::new));
    }

    public TaskDto getTaskByStatus(String status) {
        return taskMapper.toDto(taskRepository.findByStatus(status).orElseThrow(TaskNotFoundException::new));

    }

    public TaskDto createTask(TaskDto taskDto) {
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setStatus(taskDto.status());
        return taskMapper.toDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(id);
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
}
