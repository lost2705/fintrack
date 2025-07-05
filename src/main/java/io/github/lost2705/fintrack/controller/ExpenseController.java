package io.github.lost2705.fintrack.controller;

import io.github.lost2705.fintrack.model.Expense;
import io.github.lost2705.fintrack.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseRepository expenseRepository;

    @GetMapping
    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }
}