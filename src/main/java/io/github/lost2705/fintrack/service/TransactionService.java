package io.github.lost2705.fintrack.service;

import io.github.lost2705.fintrack.dto.TransactionDto;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<TransactionDto> findAll();

    TransactionDto findById(Long id);

    TransactionDto create(TransactionDto dto);

    TransactionDto update(Long id, TransactionDto dto);

    void delete(Long id);

    List<TransactionDto> findAllFiltered(Long categoryId, LocalDate from, LocalDate to);
}
