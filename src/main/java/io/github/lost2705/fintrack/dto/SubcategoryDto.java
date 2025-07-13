package io.github.lost2705.fintrack.dto;

import lombok.Data;

@Data
public class SubcategoryDto {
    private Long id;
    private String name;
    private Long categoryId;
}
