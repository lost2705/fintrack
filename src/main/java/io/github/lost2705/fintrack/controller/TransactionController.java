package io.github.lost2705.fintrack.controller;

import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Транзакции", description = "Управление расходами и доходами")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Получить список всех транзакций")
    public List<TransactionDto> getAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить транзакцию по ID")
    public TransactionDto getById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новую транзакцию")
    public TransactionDto create(@Valid @RequestBody TransactionDto dto) {
        return transactionService.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить транзакцию")
    public TransactionDto update(@PathVariable Long id, @Valid @RequestBody TransactionDto dto) {
        return transactionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить транзакцию")
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }

    @GetMapping("/filter")
    @Operation(summary = "Фильтрация транзакций по дате и категории")
    public List<TransactionDto> filter(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return transactionService.findAllFiltered(categoryId, from, to);
    }
}
