package com.K955.ExpenseTracker.Service.Impl;

import com.K955.ExpenseTracker.DTOs.Auth.AuthResponse;
import com.K955.ExpenseTracker.DTOs.Auth.LoginRequest;
import com.K955.ExpenseTracker.DTOs.Auth.SignupRequest;
import com.K955.ExpenseTracker.Entity.User;
import com.K955.ExpenseTracker.Repository.UserRepository;
import com.K955.ExpenseTracker.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest request) {
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(user);
        return new AuthResponse("token", user.getName(), user.getEmail());
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        return null;
    }
}
