package com.K955.ExpenseTracker.DTOs.Expense;

import com.K955.ExpenseTracker.Enums.Expense.Category;

public record ExpenseRequest(
        String name,
        String amount,
        Category category
) {
}
