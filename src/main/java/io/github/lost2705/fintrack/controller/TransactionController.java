package io.github.lost2705.fintrack.controller;



import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.dto.TransactionRequest;
import io.github.lost2705.fintrack.dto.TransactionResponse;
import io.github.lost2705.fintrack.mapper.TransactionMapper;
import io.github.lost2705.fintrack.model.Transaction;
import io.github.lost2705.fintrack.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @Operation(summary = "Создать новую транзакцию")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Транзакция успешно создана"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest request) {
        TransactionDto dto = transactionMapper.toDto(request);
        Transaction created = transactionService.create(dto); // Возвращаем Transaction
        TransactionResponse response = transactionMapper.toResponse(created); // Маппим Transaction в Response
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Получить список транзакций с фильтрами (опционально)")
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @Parameter(description = "Категория") @RequestParam(required = false) String category,
            @Parameter(description = "Дата с") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @Parameter(description = "Дата по") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        List<TransactionDto> dtos = transactionService.findAllFiltered(null, from, to);
        List<TransactionResponse> responses = dtos.stream()
                .map(transactionMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
