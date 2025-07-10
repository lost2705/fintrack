package io.github.lost2705.fintrack.controller;

import io.github.lost2705.fintrack.dto.CategoryDto;
import io.github.lost2705.fintrack.dto.CategoryRequest;
import io.github.lost2705.fintrack.dto.CategoryResponse;
import io.github.lost2705.fintrack.mapper.CategoryMapper;
import io.github.lost2705.fintrack.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Категории", description = "Управление категориями транзакций")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    @Operation(summary = "Получить список всех категорий")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> responses = categoryService.findAll().stream()
                .map(dto -> new CategoryResponse(dto.getId(), dto.getName()))
                .toList();
        return ResponseEntity.ok(responses);
    }


    @PostMapping
    @Operation(summary = "Создать новую категорию")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryDto dto = categoryMapper.toDto(request);
        CategoryDto created = categoryService.create(dto);
        CategoryResponse response = new CategoryResponse(created.getId(), created.getName());
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить категорию по ID")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        CategoryDto dto = categoryMapper.toDto(request);
        CategoryDto updated = categoryService.update(id, dto);
        CategoryResponse response = new CategoryResponse(updated.getId(), updated.getName());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить категорию по ID")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
