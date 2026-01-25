package com.K955.ExpenseTracker.Service.Impl;

import com.K955.ExpenseTracker.DTOs.User.UpdateUserRequest;
import com.K955.ExpenseTracker.DTOs.User.UserProfileResponse;
import com.K955.ExpenseTracker.Entity.User;
import com.K955.ExpenseTracker.Errors.BadRequestException;
import com.K955.ExpenseTracker.Errors.ResourceNotFoundException;
import com.K955.ExpenseTracker.Mapper.UserMapper;
import com.K955.ExpenseTracker.Repository.UserRepository;
import com.K955.ExpenseTracker.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileResponse getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        return userMapper.toUserProfileResponseFromUser(user);
    }

    @Override
    public UserProfileResponse updateUserProfile(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return userMapper.toUserProfileResponseFromUser(user);
    }

    @Override
    public void deleteUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", username));
    }
}
