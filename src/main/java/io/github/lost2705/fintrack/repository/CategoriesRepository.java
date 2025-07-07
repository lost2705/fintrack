package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
