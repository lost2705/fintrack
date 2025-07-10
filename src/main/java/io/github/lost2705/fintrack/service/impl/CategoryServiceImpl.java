package io.github.lost2705.fintrack.service.impl;

import io.github.lost2705.fintrack.dto.CategoryDto;
import io.github.lost2705.fintrack.mapper.CategoryMapper;
import io.github.lost2705.fintrack.model.Category;
import io.github.lost2705.fintrack.repository.CategoryRepository;
import io.github.lost2705.fintrack.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category entity = mapper.toEntity(dto);
        Category saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: id = " + id));
        return mapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: id = " + id));
        existing.setName(dto.getName()); // можно расширить под поля DTO
        return mapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Категория не найдена: id = " + id);
        }
        repository.deleteById(id);
    }
}
