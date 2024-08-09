package com.financeapp.financeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Entity
@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;
//    @JsonIgnore // do research on this
    private String password;
    private String email;

//    @OneToMany(mappedBy = "user")
//    @Nullable
//    private Set<Expense> expenses;
//
//    @OneToMany(mappedBy = "user")
//    @Nullable
//    private Set<Budget> budgets;

}

