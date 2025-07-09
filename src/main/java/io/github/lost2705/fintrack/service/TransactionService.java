package io.github.lost2705.fintrack.service;

import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.dto.TransactionResponse;
import io.github.lost2705.fintrack.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<TransactionDto> findAll();

    TransactionDto findById(Long id);

    Transaction create(TransactionDto dto);

    TransactionDto update(Long id, TransactionDto dto);

    void delete(Long id);

    List<TransactionDto> findAllFiltered(Long categoryId, LocalDate from, LocalDate to);

    List<TransactionResponse> findTransactions(String category, LocalDate from, LocalDate to);
}
