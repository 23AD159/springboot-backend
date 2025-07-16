package com.example.springintern.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private RegisterDetails assignedEmployee;
}

