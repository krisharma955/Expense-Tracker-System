package com.K955.ExpenseTracker.Controllers;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseRequest;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.Entity.Expense;
import com.K955.ExpenseTracker.Service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest request) {
        Long userId = 1L;
        return ResponseEntity.ok(expenseService.createExpense(userId, request));
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long expenseId) {
        Long userId = 1L;
        return ResponseEntity.ok(expenseService.getExpenseById(userId, expenseId));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        Long userId = 1L;
        return ResponseEntity.ok(expenseService.getAllExpenses(userId));
    }

    @PatchMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long expenseId) {
        Long userId = 1L;
        return ResponseEntity.ok(expenseService.updateExpense(userId, expenseId));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        Long userId = 1L;
        return ResponseEntity.ok(expenseService.softDelete(userId, expenseId));
    }

}
