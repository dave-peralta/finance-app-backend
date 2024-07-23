package com.financeapp.financeapp.repository;

import com.financeapp.financeapp.entity.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BudgetRepository extends MongoRepository<Budget, String> {
    List<Budget> findByUserId(String id);
}

