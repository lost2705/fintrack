package io.github.lost2705.fintrack.controller;

import io.github.lost2705.fintrack.dto.CategoryDto;
import io.github.lost2705.fintrack.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Категории", description = "Операции с категориями расходов")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Получить список всех категорий")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    @Operation(summary = "Создать новую категорию")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto dto) {
        return categoryService.create(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить категорию по ID")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить категорию по ID")
    public CategoryDto updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить категорию по ID")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}

