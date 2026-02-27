package com.example.gestor_de_gastos.controller;

import com.example.gestor_de_gastos.entity.Expense;
import com.example.gestor_de_gastos.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseServiceImpl){
        this.expenseService = expenseServiceImpl;
    }

    @GetMapping("")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenses =  this.expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense){
        return new ResponseEntity<>(this.expenseService.postExpense(expense), HttpStatus.CREATED);
    }
}
