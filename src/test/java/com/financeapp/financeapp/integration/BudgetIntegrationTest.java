package com.financeapp.financeapp.integration;

import com.financeapp.financeapp.entity.Budget;
import com.financeapp.financeapp.repository.BudgetRepository;
import com.financeapp.financeapp.service.BudgetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class BudgetIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetService budgetService;

    private String budgetId;

    @BeforeEach
    public void setup() {
        Budget budget = new Budget();
        budget.setAmount(new BigDecimal("1000.00"));
        budget.setUserId("testUser");
        Budget savedBudget = budgetRepository.save(budget);
        budgetId = savedBudget.getId();
    }

    @AfterEach
    public void cleanup() {
        budgetRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void addBudget_success_when_validRequest() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:addBudgetRequest.json");
        String content = new String(resource.getInputStream().readAllBytes());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/budgets")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Budget"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(500.00));
    }

    @Test
    @WithMockUser
    public void getBudgetsByUserId_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/budgets/user/testUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test Budget"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(1000.00));
    }

    @Test
    @WithMockUser
    public void getAllBudgets_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/budgets/getAllBudgets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test Budget"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(1000.00));
    }

    @Test
    @WithMockUser
    public void updateBudget_success() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:updateBudgetRequest.json");
        String content = new String(resource.getInputStream().readAllBytes());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/budgets")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Budget"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(1500.00));
    }

    @Test
    @WithMockUser
    public void deleteBudget_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/budgets/{id}", budgetId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void deleteBudget_badRequest_when_invalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/budgets/invalidId"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}