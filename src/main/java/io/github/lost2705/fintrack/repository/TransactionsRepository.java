package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}