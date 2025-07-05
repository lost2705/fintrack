package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public class CategoryRepository {

    public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    }
}
