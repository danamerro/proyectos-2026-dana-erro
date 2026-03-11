package com.example.gestor_de_gastos.service;

import com.example.gestor_de_gastos.entity.Expense;

import java.util.List;

public interface ExpenseService {
    public Expense postExpense(Expense expense);
    public List<Expense> getAllExpenses();
    public Expense getExpenseById(Long id);
    public Expense updateExpense(Long id,Expense updateExpense);
}
