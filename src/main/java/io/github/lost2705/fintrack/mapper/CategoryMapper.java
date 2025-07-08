package io.github.lost2705.fintrack.mapper;

import io.github.lost2705.fintrack.dto.CategoryDto;
import io.github.lost2705.fintrack.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
