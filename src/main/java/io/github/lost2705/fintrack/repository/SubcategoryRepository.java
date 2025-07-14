package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
