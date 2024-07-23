package com.financeapp.financeapp.controller;

import com.financeapp.financeapp.entity.Budget;
import com.financeapp.financeapp.entity.User;
import com.financeapp.financeapp.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Budget> addBudget(@RequestBody Budget budget) {
        Budget newBudget = budgetService.addBudget(budget);
        return ResponseEntity.ok(newBudget);
    }

    @GetMapping("/user/{userId}")
    public List<Budget> getBudgetsByUserId(@PathVariable String userId) {
        return budgetService.getBudgetsByUserId(userId);
    }

    @GetMapping("getAllBudgets")
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = budgetService.getAllBudgets();
        return ResponseEntity.ok(budgets);
    }

    @PutMapping
    public ResponseEntity<Budget> updateBudget(@RequestBody Budget budget) {
        Budget updatedBudget = budgetService.updateBudget(budget);
        return ResponseEntity.ok(updatedBudget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable String id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok().build();
    }
}
