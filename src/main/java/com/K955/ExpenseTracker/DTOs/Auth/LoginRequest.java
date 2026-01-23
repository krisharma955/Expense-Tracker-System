package com.K955.ExpenseTracker.DTOs.Auth;

public record LoginRequest(
        String email,
        String password
) {
}
