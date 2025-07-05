package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
