package io.github.lost2705.fintrack.controller;

import io.github.lost2705.fintrack.dto.SubcategoryDto;
import io.github.lost2705.fintrack.dto.SubcategoryRequest;
import io.github.lost2705.fintrack.dto.SubcategoryResponse;
import io.github.lost2705.fintrack.mapper.SubcategoryMapper;
import io.github.lost2705.fintrack.service.SubcategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubcategoryService service;
    private final SubcategoryMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubcategoryResponse>> getAll() {
        List<SubcategoryResponse> responses = service.findAll().stream()
                .map(dto -> mapper.toResponse(mapper.toEntity(dto)))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<SubcategoryResponse> create(@Valid @RequestBody SubcategoryRequest request) {
        SubcategoryDto dto = mapper.toDto(request);
        SubcategoryDto created = service.create(dto);
        return ResponseEntity.status(201).body(mapper.toResponse(mapper.toEntity(created)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryResponse> update(@PathVariable Long id, @Valid @RequestBody SubcategoryRequest request) {
        SubcategoryDto dto = mapper.toDto(request);
        SubcategoryDto updated = service.update(id, dto);
        return ResponseEntity.ok(mapper.toResponse(mapper.toEntity(updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
