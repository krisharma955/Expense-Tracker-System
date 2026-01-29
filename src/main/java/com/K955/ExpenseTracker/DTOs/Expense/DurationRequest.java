package com.K955.ExpenseTracker.DTOs.Expense;

public record DurationRequest(
        Integer startDay,
        Integer startMonth,
        Integer startYear,
        Integer endDay,
        Integer endMonth,
        Integer endYear
) {
}
