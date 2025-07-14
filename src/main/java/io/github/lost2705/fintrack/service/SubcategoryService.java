package io.github.lost2705.fintrack.service;

import io.github.lost2705.fintrack.dto.SubcategoryDto;

import java.util.List;

public interface SubcategoryService {
    List<SubcategoryDto> findAll();

    SubcategoryDto findById(Long id);

    SubcategoryDto create(SubcategoryDto dto);

    SubcategoryDto update(Long id, SubcategoryDto dto);

    void delete(Long id);
}
