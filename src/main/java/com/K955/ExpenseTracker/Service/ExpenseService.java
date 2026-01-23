package com.K955.ExpenseTracker.Service;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseRequest;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseSummaryResponse;
import com.K955.ExpenseTracker.Entity.Expense;

import java.util.List;

public interface ExpenseService {
    ExpenseResponse createExpense(Long userId, ExpenseRequest request);

    ExpenseResponse getExpenseById(Long userId, Long expenseId);

    List<ExpenseSummaryResponse> getAllExpenses(Long userId);

    ExpenseResponse updateExpense(Long userId, Long expenseId, ExpenseRequest request);

    void deleteExpense(Long userId, Long expenseId);
}
