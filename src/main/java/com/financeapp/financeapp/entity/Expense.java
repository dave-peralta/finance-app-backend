package com.financeapp.financeapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Document(collection = "expenses")
public class Expense {
    @Id
    private String id;

    private String userId;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private String category;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}

