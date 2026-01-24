package com.K955.ExpenseTracker.Service;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseRequest;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseSummaryResponse;
import com.K955.ExpenseTracker.Entity.Expense;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ExpenseService {
    ExpenseResponse createExpense(Long userId, ExpenseRequest request);

    ExpenseResponse getExpenseById(Long userId, Long expenseId);

    List<ExpenseSummaryResponse> getAllExpenses(Long userId);

    ExpenseResponse updateExpense(Long userId, Long expenseId, ExpenseRequest request);

    void deleteExpense(Long userId, Long expenseId);

    List<ExpenseSummaryResponse> getTravelExpenses(Long userId);

    List<ExpenseSummaryResponse> getShoppingExpenses(Long userId);

    List<ExpenseSummaryResponse> getMedicalExpenses(Long userId);

    List<ExpenseSummaryResponse> getFoodExpenses(Long userId);

    List<ExpenseSummaryResponse> getEducationExpenses(Long userId);

    List<ExpenseSummaryResponse> getHousingExpenses(Long userId);

    List<ExpenseSummaryResponse> getEntertainmentExpenses(Long userId);
}
