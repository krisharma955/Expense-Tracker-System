package com.K955.ExpenseTracker.DTOs.Auth;

public record AuthResponse(
        String token,
        String name,
        String email
) {
}
