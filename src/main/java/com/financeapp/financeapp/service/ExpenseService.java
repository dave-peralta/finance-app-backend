package com.financeapp.financeapp.service;

import com.financeapp.financeapp.entity.Budget;
import com.financeapp.financeapp.entity.Expense;
import com.financeapp.financeapp.entity.User;
import com.financeapp.financeapp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpenseByUserId(String userId) {
        return expenseRepository.findByUserId(userId);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}

