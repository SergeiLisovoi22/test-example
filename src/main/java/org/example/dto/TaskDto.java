package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskDto(@NotBlank(message = "Title is required") String title,
                      @NotBlank(message = "Description is required") String description,
                      @NotBlank(message = "Status is required") String status) {

}
