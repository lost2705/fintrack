package io.github.lost2705.fintrack.service.impl;

import io.github.lost2705.fintrack.dto.SubcategoryDto;
import io.github.lost2705.fintrack.mapper.SubcategoryMapper;
import io.github.lost2705.fintrack.model.Subcategory;
import io.github.lost2705.fintrack.repository.SubcategoryRepository;
import io.github.lost2705.fintrack.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository repository;
    private final SubcategoryMapper mapper;

    @Override
    public List<SubcategoryDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public SubcategoryDto findById(Long id) {
        Subcategory subcategory = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Подкатегория не найдена: id = " + id));
        return mapper.toDto(subcategory);
    }

    @Override
    public SubcategoryDto create(SubcategoryDto dto) {
        Subcategory saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public SubcategoryDto update(Long id, SubcategoryDto dto) {
        Subcategory existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Подкатегория не найдена: id = " + id));
        existing.setName(dto.getName());
        return mapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
