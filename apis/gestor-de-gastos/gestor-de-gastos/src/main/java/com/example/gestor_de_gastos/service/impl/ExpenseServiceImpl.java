package com.example.gestor_de_gastos.service.impl;

import com.example.gestor_de_gastos.entity.Expense;
import com.example.gestor_de_gastos.entity.enums.CategoryEnum;
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
        if(!expenseRepository.existsById(id)){
            throw new ExpenseNotFoundException("Expense not found with ID " + id);
        }

        return this.expenseRepository.findById(id).get();
    }

    @Override
    public Expense updateExpense(Long id, Expense updateExpense) {
        if(!this.expenseRepository.existsById(id)){
            throw new ExpenseNotFoundException("Expense not found with ID"+id);
        }
        Expense existingExpense = expenseRepository.findById(id).get();

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


    @Override
    public void deleteExpenseById(Long id) {
        if (!this.expenseRepository.existsById(id)) {
            throw new ExpenseNotFoundException("Expense not found with ID " + id);
        }
        this.expenseRepository.deleteById(id);
    }

    @Override
    public void deleteAllExpenses(){
        this.expenseRepository.findAll();
        this.expenseRepository.deleteAll();
    }

    @Override
    public List<Expense> getExpensesByCategory(CategoryEnum category) {
        return expenseRepository.findByCategory(category);
    }
}
