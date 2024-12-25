package org.example.service;

import org.example.entity.Task;
import org.example.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitService implements CommandLineRunner {

    private final TaskRepository taskRepository;

    public InitService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        saveTestTasks();
    }

    private void saveTestTasks() {
        Task task1 = new Task();
        task1.setTitle("Jira1");
        task1.setDescription("JiraCodeTask1");
        task1.setStatus("OPEN");

        Task task2 = new Task();
        task1.setTitle("Jira2");
        task1.setDescription("JiraCodeTask2");
        task1.setStatus("CLOSED");

        Task task3 = new Task();
        task1.setTitle("Jira3");
        task1.setDescription("JiraCodeTask3");
        task1.setStatus("NEW");

        taskRepository.saveAll(Arrays.asList(task1, task2, task3));
    }
}
