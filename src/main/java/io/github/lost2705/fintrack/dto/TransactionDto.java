package io.github.lost2705.fintrack.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Транзакция: доход или расход")
public class TransactionDto {

    @Schema(description = "ID транзакции", example = "42")
    private Long id;

    @NotNull
    @Positive
    @Schema(description = "Сумма транзакции", example = "1500.00")
    private BigDecimal amount;

    @NotNull
    @Schema(description = "Дата операции", example = "2025-07-08")
    private LocalDate date;

    @Size(max = 255)
    @Schema(description = "Описание", example = "Оплата подписки на Spotify")
    private String description;

    @NotNull
    @Schema(description = "ID категории", example = "3")
    private Long categoryId;
}
