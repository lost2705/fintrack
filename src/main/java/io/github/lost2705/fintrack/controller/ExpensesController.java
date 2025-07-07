package io.github.lost2705.fintrack.controller;

import io.github.lost2705.fintrack.model.Expenses;
import io.github.lost2705.fintrack.repository.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpensesController {

    private final ExpensesRepository expenseRepository;

    @GetMapping
    public List<Expenses> getAll() {
        return expenseRepository.findAll();
    }
}