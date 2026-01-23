package com.K955.ExpenseTracker.DTOs.Expense;

import com.K955.ExpenseTracker.Enums.Expense.Category;

public record ExpenseResponse(
        String name,
        String amount,
        Category category
) {
}
