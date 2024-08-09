package com.financeapp.financeapp.service;

import com.financeapp.financeapp.entity.Budget;
import com.financeapp.financeapp.repository.BudgetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BudgetServiceTest {

    @Mock
    private BudgetRepository budgetRepository;

    @InjectMocks
    private BudgetService budgetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBudget() {
        Budget budget = new Budget();
        when(budgetRepository.save(any(Budget.class))).thenReturn(budget);

        Budget result = budgetService.addBudget(budget);

        assertNotNull(result);
        verify(budgetRepository, times(1)).save(budget);
    }

    @Test
    void testGetBudgetsByUserId() {
        String userId = "669f59c7b409d42fe414c3b7";
        List<Budget> budgets = Arrays.asList(new Budget(), new Budget());
        when(budgetRepository.findByUserId(userId)).thenReturn(budgets);

        List<Budget> result = budgetService.getBudgetsByUserId(userId);

        assertEquals(2, result.size());
        verify(budgetRepository, times(1)).findByUserId(userId);
    }

}