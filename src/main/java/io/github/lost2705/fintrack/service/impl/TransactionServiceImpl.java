package io.github.lost2705.fintrack.service.impl;

import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.dto.TransactionResponse;
import io.github.lost2705.fintrack.mapper.TransactionMapper;
import io.github.lost2705.fintrack.model.Category;
import io.github.lost2705.fintrack.model.Transaction;
import io.github.lost2705.fintrack.repository.CategoryRepository;
import io.github.lost2705.fintrack.repository.TransactionRepository;
import io.github.lost2705.fintrack.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    @Override
    public TransactionDto findById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Транзакция не найдена: id = " + id));
    }

    @Override
    public Transaction create(TransactionDto dto) {
        Transaction entity = transactionMapper.toEntity(dto);
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: id = " + dto.getCategoryId()));
        entity.setCategory(category);
        return transactionRepository.save(entity);
    }

    @Override
    public TransactionDto update(Long id, TransactionDto dto) {
        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Транзакция не найдена: id = " + id));

        existing.setAmount(dto.getAmount());
        existing.setDate(dto.getDate());
        existing.setDescription(dto.getDescription());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: id = " + dto.getCategoryId()));
        existing.setCategory(category);

        return transactionMapper.toDto(transactionRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionResponse> findTransactions(String category, LocalDate from, LocalDate to) {
        return transactionRepository.findAll().stream()
                .filter(t -> category == null || Objects.equals(t.getCategory().getName(), category))
                .filter(t -> from == null || !t.getDate().isBefore(from))
                .filter(t -> to == null || !t.getDate().isAfter(to))
                .map(transactionMapper::toResponse)
                .toList();
    }
}