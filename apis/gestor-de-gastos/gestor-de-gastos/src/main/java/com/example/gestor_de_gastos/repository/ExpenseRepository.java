package com.example.gestor_de_gastos.repository;

import com.example.gestor_de_gastos.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
