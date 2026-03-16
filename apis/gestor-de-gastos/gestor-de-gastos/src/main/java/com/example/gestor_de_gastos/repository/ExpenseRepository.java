package com.example.gestor_de_gastos.repository;

import com.example.gestor_de_gastos.entity.Expense;
import com.example.gestor_de_gastos.entity.enums.CategoryEnum;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(CategoryEnum category);
}
