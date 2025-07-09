package io.github.lost2705.fintrack.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        @NotNull(message = "Сумма обязательна")
        @DecimalMin(value = "0.01", message = "Сумма должна быть больше 0")
        BigDecimal amount,

        @NotNull(message = "ID категории обязателен")
        Long categoryId,

        @NotBlank(message = "Описание обязательно")
        String description,

        @NotNull(message = "Дата обязательна")
        LocalDate date
) {}
