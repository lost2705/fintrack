package io.github.lost2705.fintrack.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Название обязательно")
        String name
) {}
