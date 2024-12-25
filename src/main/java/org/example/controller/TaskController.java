package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.TaskDto;
import org.example.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/title")
    public TaskDto getByTitle(@RequestParam String title) {
        return taskService.getTaskByTitle(title);
    }

    @GetMapping("/status")
    public TaskDto getByStatus(@RequestParam String status) {
        return taskService.getTaskByStatus(status);
    }

    @PostMapping("/create")
    public TaskDto createTask(@Valid @RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }
}
