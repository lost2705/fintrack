package io.github.lost2705.fintrack.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String description;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}