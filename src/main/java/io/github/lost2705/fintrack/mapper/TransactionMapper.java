package io.github.lost2705.fintrack.mapper;

import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.model.Category;
import io.github.lost2705.fintrack.model.Transaction;
import io.github.lost2705.fintrack.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final CategoryRepository categoryRepository;

    public TransactionDto toDto(Transaction entity) {
        TransactionDto dto = new TransactionDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setDate(entity.getDate());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategory().getId());
        return dto;
    }

    public Transaction toEntity(TransactionDto dto) {
        Transaction entity = new Transaction();
        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: id = " + dto.getCategoryId()));
        entity.setCategory(category);

        return entity;
    }
}
