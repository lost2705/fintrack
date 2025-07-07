package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}