package io.github.lost2705.fintrack.mapper;

import io.github.lost2705.fintrack.dto.SubcategoryDto;
import io.github.lost2705.fintrack.dto.SubcategoryRequest;
import io.github.lost2705.fintrack.dto.SubcategoryResponse;
import io.github.lost2705.fintrack.model.Category;
import io.github.lost2705.fintrack.model.Subcategory;
import io.github.lost2705.fintrack.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubcategoryMapper {

    private final CategoryRepository categoryRepository;

    public SubcategoryDto toDto(SubcategoryRequest request) {
        SubcategoryDto dto = new SubcategoryDto();
        dto.setName(request.name());
        dto.setCategoryId(request.categoryId());
        return dto;
    }

    public SubcategoryDto toDto(Subcategory entity) {
        SubcategoryDto dto = new SubcategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategoryId(entity.getCategory().getId());
        return dto;
    }

    public Subcategory toEntity(SubcategoryDto dto) {
        Subcategory entity = new Subcategory();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: id = " + dto.getCategoryId()));
        entity.setCategory(category);
        return entity;
    }

    public SubcategoryResponse toResponse(Subcategory entity) {
        return new SubcategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getCategory().getName()
        );
    }
}
