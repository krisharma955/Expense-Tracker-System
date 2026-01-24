package com.K955.ExpenseTracker.Security;

public record JwtUserResponse(
        Long userId,
        String email
) {
}
