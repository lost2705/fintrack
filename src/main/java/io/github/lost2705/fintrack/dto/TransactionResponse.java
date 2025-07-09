package io.github.lost2705.fintrack.dto;

import io.github.lost2705.fintrack.model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        String category,
        String description,
        LocalDate date
) {}

