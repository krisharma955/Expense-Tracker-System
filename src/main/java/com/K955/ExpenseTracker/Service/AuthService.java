package com.K955.ExpenseTracker.Service;

import com.K955.ExpenseTracker.DTOs.Auth.AuthResponse;
import com.K955.ExpenseTracker.DTOs.Auth.LoginRequest;
import com.K955.ExpenseTracker.DTOs.Auth.SignupRequest;
import org.apache.coyote.BadRequestException;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
