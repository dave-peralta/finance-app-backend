package com.financeapp.financeapp.service;

import com.financeapp.financeapp.entity.Budget;
import com.financeapp.financeapp.entity.User;
import com.financeapp.financeapp.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service    // turns it into a spring bean -- for dependency injection; removes the need for constructors
public class BudgetService {
    // 2 ways of dependency injection: autowired or constructor; preferred way is through constructors; read up why constructors >> autowired
    //    @NoArgsConstructor
    //    @AllArgsConstructor
    //    @RequiredArgsConstructor - spring decides what to inject
    // no need to use both constructor and autowired
    @Autowired
    private BudgetRepository budgetRepository;

    public Budget addBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> getBudgetsByUserId(String userId) {
        return budgetRepository.findByUserId(userId);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public List<Budget> getAllBudgetsByUserId(String userId) {
        return budgetRepository.findByUserId(userId);
    }

    // @Bean -- research; usually used in config files
    public Budget updateBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public void deleteBudget(String id) {
        budgetRepository.deleteById(id);
    }

}

