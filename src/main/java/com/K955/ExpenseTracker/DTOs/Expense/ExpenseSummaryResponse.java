package com.K955.ExpenseTracker.DTOs.Expense;

import com.K955.ExpenseTracker.Enums.Expense.Category;

import java.time.Instant;

public record ExpenseSummaryResponse(
        Long id,
        String name,
        String amount,
        Category category,
        Instant createdAt,
        Instant updatedAt
) {
}
