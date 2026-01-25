package com.K955.ExpenseTracker.Service.Impl;

import com.K955.ExpenseTracker.DTOs.Auth.AuthResponse;
import com.K955.ExpenseTracker.DTOs.Auth.LoginRequest;
import com.K955.ExpenseTracker.DTOs.Auth.SignupRequest;
import com.K955.ExpenseTracker.Entity.User;
import com.K955.ExpenseTracker.Errors.BadRequestException;
import com.K955.ExpenseTracker.Mapper.UserMapper;
import com.K955.ExpenseTracker.Repository.UserRepository;
import com.K955.ExpenseTracker.Security.JwtService;
import com.K955.ExpenseTracker.Service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserMapper userMapper;

    @Override
    public AuthResponse signup(SignupRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if(user.isPresent()) {
            throw new BadRequestException("User already exists!");
        }
        User newUser = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        String accessToken = jwtService.generateAccessToken(newUser);
        userRepository.save(newUser);
        return new AuthResponse(accessToken, userMapper.toUserProfileResponseFromUser(newUser));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);

        return new AuthResponse(accessToken, userMapper.toUserProfileResponseFromUser(user));
    }
}
