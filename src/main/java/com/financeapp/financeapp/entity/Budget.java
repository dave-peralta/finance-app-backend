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

// Study @Table annotation
//@Table()
@Document(collection = "budget")
public class Budget {
    @Id
    private String id;

    private String userId;
    private BigDecimal amount;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}

