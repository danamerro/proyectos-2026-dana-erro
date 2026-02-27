package com.example.gestor_de_gastos.service.impl;

import com.example.gestor_de_gastos.entity.Expense;
import com.example.gestor_de_gastos.repository.ExpenseRepository;
import com.example.gestor_de_gastos.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository  = expenseRepository;
    }
    @Override
    public Expense postExpense(Expense expense) {
        return this.expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expensesList = this.expenseRepository.findAll();
        return expensesList;
    }
}
