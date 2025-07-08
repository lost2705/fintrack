package io.github.lost2705.fintrack.service.impl;

import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.mapper.TransactionMapper;
import io.github.lost2705.fintrack.model.Transaction;
import io.github.lost2705.fintrack.repository.TransactionRepository;
import io.github.lost2705.fintrack.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    @Override
    public TransactionDto findById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Транзакция не найдена: id = " + id));
        return transactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDto create(TransactionDto dto) {
        Transaction entity = transactionMapper.toEntity(dto);
        Transaction saved = transactionRepository.save(entity);
        return transactionMapper.toDto(saved);
    }

    @Override
    public TransactionDto update(Long id, TransactionDto dto) {
        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Транзакция не найдена: id = " + id));

        existing.setAmount(dto.getAmount());
        existing.setDate(dto.getDate());
        existing.setDescription(dto.getDescription());
        existing.setCategory(
                transactionMapper.toEntity(dto).getCategory() // повторно загружаем категорию через маппер
        );

        return transactionMapper.toDto(transactionRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Транзакция не найдена: id = " + id);
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionDto> findAllFiltered(Long categoryId, LocalDate from, LocalDate to) {
        return transactionRepository.findFiltered(categoryId, from, to).stream()
                .map(transactionMapper::toDto)
                .toList();
    }

}
