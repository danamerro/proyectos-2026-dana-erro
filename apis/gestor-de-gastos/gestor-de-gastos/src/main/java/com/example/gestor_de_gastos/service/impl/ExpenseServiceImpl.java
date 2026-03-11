package com.example.gestor_de_gastos.service.impl;

import com.example.gestor_de_gastos.entity.Expense;
import com.example.gestor_de_gastos.exception.ExpenseNotFoundException;
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

    @Override
    public Expense getExpenseById(Long id){
        return this.expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFoundException("Expense not found with ID"+id));
    }

    @Override
    public Expense updateExpense(Long id,Expense updateExpense){
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with ID"+id));
        if(updateExpense.getAmount()!=null){
            existingExpense.setAmount(updateExpense.getAmount());
        }if(updateExpense.getCategory()!=null){
            existingExpense.setCategory(updateExpense.getCategory());
        }if(updateExpense.getDescription()!=null){
            existingExpense.setDescription(updateExpense.getDescription());
        } if(updateExpense.getDate()!=null){
            existingExpense.setDate(updateExpense.getDate());
        }
        return this.expenseRepository.save(existingExpense);
    }
}
