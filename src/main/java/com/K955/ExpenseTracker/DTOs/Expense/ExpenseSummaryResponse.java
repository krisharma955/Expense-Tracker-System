package com.K955.ExpenseTracker.DTOs.Expense;

import java.time.Instant;

public record ExpenseSummaryResponse(
        Long id,
        String name,
        String amount,
        Instant createdAt,
        Instant updatedAt
) {
}
