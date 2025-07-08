package io.github.lost2705.fintrack.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Категория расходов")
public class CategoryDto {

    @Schema(description = "ID категории", example = "1")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Schema(description = "Название категории", example = "Продукты")
    private String name;
}
