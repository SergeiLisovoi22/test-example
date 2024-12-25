package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.PostgresContainer;
import org.example.dto.TaskDto;
import org.example.entity.Task;
import org.example.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TaskControllerTest extends PostgresContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;
    private Task task1;

    @BeforeEach
    void setUp() {
        saveTasks();
    }

    @Test
    @DisplayName("Test getting task by title")
    void getByTitleSuccess() throws Exception {
        mockMvc.perform(get("/v1/task/title")
                        .param("title", "Jira1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Jira1")))
                .andExpect(jsonPath("$.description", is("JiraCodeTask1")))
                .andExpect(jsonPath("$.status", is("OPEN")));
    }

    @Test
    @DisplayName("Test getting task by title not found")
    void getByTitleNotFound() throws Exception {
        mockMvc.perform(get("/v1/task/title")
                        .param("title", "Jira33")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test getting task by status")
    void getByStatusSuccess() throws Exception {
        mockMvc.perform(get("/v1/task/status")
                        .param("status", "NEW")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Jira2")))
                .andExpect(jsonPath("$.description", is("JiraCodeTask2")))
                .andExpect(jsonPath("$.status", is("NEW")));
    }

    @Test
    @DisplayName("Test getting task by status not found")
    void getByStatusNotFound() throws Exception {
        mockMvc.perform(get("/v1/task/status")
                        .param("status", "IN_PROGRESS")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test create task success")
    void createTaskSuccess() throws Exception {
        TaskDto createdTask = new TaskDto("Jira3", "JiraCodeTask3", "NEW");

        mockMvc.perform(post("/v1/task/create")
                        .content(asJsonString(createdTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Test create task failed")
    void createTaskFail() throws Exception {
        TaskDto invalidTask = new TaskDto("", "", "");

        mockMvc.perform(post("/v1/task/create")
                        .content(asJsonString(invalidTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Title is required")))
                .andExpect(jsonPath("$.description", is("Description is required")))
                .andExpect(jsonPath("$.status", is("Status is required")));
    }

    @Test
    @DisplayName("Test update task success")
    void updateTaskSuccess() throws Exception {
        TaskDto updatedTask = new TaskDto("Jira11", "JiraCodeTask11", "IN_PROGRESS");

        mockMvc.perform(put("/v1/task/{id}", task1.getId())
                        .content(asJsonString(updatedTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Jira11")))
                .andExpect(jsonPath("$.description", is("JiraCodeTask11")))
                .andExpect(jsonPath("$.status", is("IN_PROGRESS")));
    }

    @Test
    @DisplayName("Test update task failed")
    void updateTaskNotFound() throws Exception {
        TaskDto updatedTask = new TaskDto("Jira11", "JiraCodeTask11", "IN_PROGRESS");

        mockMvc.perform(put("/v1/task/{id}", 999)
                        .content(asJsonString(updatedTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test delete task success")
    void deleteTaskSuccess() throws Exception {
        mockMvc.perform(delete("/v1/task/{id}", task1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test delete task failed")
    void deleteTaskNotFound() throws Exception {
        mockMvc.perform(delete("/v1/task/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test get all tasks success")
    void getAllTasksSuccess() throws Exception {
        mockMvc.perform(get("/v1/task/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].title", is("Jira1")))
                .andExpect(jsonPath("$[1].title", is("Jira2")));
    }

    private void saveTasks() {
        taskRepository.deleteAll();
        task1 = new Task(null, "Jira1", "JiraCodeTask1", "OPEN");
        Task task2 = new Task(null, "Jira2", "JiraCodeTask2", "NEW");
        taskRepository.saveAll(Arrays.asList(task1, task2));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}