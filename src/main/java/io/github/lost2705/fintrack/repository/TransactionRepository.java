package io.github.lost2705.fintrack.repository;

import io.github.lost2705.fintrack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
    SELECT t FROM Transaction t
    WHERE (:categoryId IS NULL OR t.category.id = :categoryId)
      AND (:from IS NULL OR t.date >= :from)
      AND (:to IS NULL OR t.date <= :to)
""")
    List<Transaction> findFiltered(@Param("categoryId") Long categoryId,
                                   @Param("from") LocalDate from,
                                   @Param("to") LocalDate to);

}