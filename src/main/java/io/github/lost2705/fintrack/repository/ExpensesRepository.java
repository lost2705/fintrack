package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
}
