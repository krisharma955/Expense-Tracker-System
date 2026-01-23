package com.K955.ExpenseTracker.DTOs.Auth;

public record SignupRequest(
        String name,
        String email,
        String password
) {
}
