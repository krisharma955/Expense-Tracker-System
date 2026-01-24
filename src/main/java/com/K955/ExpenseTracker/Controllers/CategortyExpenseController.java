package com.K955.ExpenseTracker.Controllers;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseSummaryResponse;
import com.K955.ExpenseTracker.Security.JwtService;
import com.K955.ExpenseTracker.Service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class CategortyExpenseController {

    private final ExpenseService expenseService;

    private final JwtService jwtService;

    @GetMapping("/travel")
    public ResponseEntity<List<ExpenseSummaryResponse>> getTravelExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getTravelExpenses(userId));
    }

    @GetMapping("/shopping")
    public ResponseEntity<List<ExpenseSummaryResponse>> getShoppingExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getShoppingExpenses(userId));
    }

    @GetMapping("/medical")
    public ResponseEntity<List<ExpenseSummaryResponse>> getMedicalExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getMedicalExpenses(userId));
    }

    @GetMapping("/food")
    public ResponseEntity<List<ExpenseSummaryResponse>> getFoodExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getFoodExpenses(userId));
    }

    @GetMapping("/education")
    public ResponseEntity<List<ExpenseSummaryResponse>> getEducationExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getEducationExpenses(userId));
    }

    @GetMapping("/housing")
    public ResponseEntity<List<ExpenseSummaryResponse>> getHousingExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getHousingExpenses(userId));
    }

    @GetMapping("/entertainment")
    public ResponseEntity<List<ExpenseSummaryResponse>> getEntertainmentExpenses() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(expenseService.getEntertainmentExpenses(userId));
    }

}
