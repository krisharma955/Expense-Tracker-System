package com.K955.ExpenseTracker.DTOs.Auth;

import com.K955.ExpenseTracker.DTOs.User.UserProfileResponse;

public record AuthResponse(
        String accessToken,
        UserProfileResponse user
) {
}
