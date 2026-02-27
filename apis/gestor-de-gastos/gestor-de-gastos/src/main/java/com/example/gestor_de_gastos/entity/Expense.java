package com.example.gestor_de_gastos.entity;
import com.example.gestor_de_gastos.entity.enums.CategoryEnum;
import com.example.gestor_de_gastos.entity.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.*;
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 200)
    private String description;
    @NotNull
    @DecimalMin(value = "0.01")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;
    @NotNull(message="La categoria es obligatoria")
    private CategoryEnum category;
    @NotNull(message = "La fecha no puede ser futura")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @NotNull(message="El metodo de pago debe ser obligatorio")
    private PaymentMethodEnum paymentMethod;

    public Expense(Long id, String description, BigDecimal amount, CategoryEnum category, LocalDate date, PaymentMethodEnum paymentMethod) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public Expense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) && Objects.equals(description, expense.description) && Objects.equals(amount, expense.amount) && Objects.equals(category, expense.category) && Objects.equals(date, expense.date) && Objects.equals(paymentMethod, expense.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, category, date, paymentMethod);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", date=" + date +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
