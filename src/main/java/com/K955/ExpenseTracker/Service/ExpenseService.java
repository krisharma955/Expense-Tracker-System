package com.K955.ExpenseTracker.Service;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseRequest;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.Entity.Expense;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ExpenseService {
    ExpenseResponse createExpense(Long userId, ExpenseRequest request);

    ExpenseResponse getExpenseById(Long userId, Long expenseId);

    List<Expense> getAllExpenses(Long userId);

    ExpenseResponse updateExpense(Long userId, Long expenseId);

    Void softDelete(Long userId, Long expenseId);
}
