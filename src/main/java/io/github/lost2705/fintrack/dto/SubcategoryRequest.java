package io.github.lost2705.fintrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubcategoryRequest(
        @NotBlank(message = "Название обязательно") String name,
        @NotNull(message = "ID категории обязателен") Long categoryId
) {}
