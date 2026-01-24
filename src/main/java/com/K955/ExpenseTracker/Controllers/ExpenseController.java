package com.K955.ExpenseTracker.Controllers;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseRequest;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseSummaryResponse;
import com.K955.ExpenseTracker.Security.JwtService;
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

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest request) {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.createExpense(userId, request));
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long expenseId) {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getExpenseById(userId, expenseId));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseSummaryResponse>> getAllExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getAllExpenses(userId));
    }

    @PatchMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long expenseId,
                                                         @RequestBody ExpenseRequest request) {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.updateExpense(userId, expenseId, request));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        Long userId = jwtService.getCurrentUserId();
        expenseService.deleteExpense(userId, expenseId);
        return ResponseEntity.noContent().build();
    }

}
