package com.K955.ExpenseTracker.Service;

import com.K955.ExpenseTracker.DTOs.User.UpdateUserRequest;
import com.K955.ExpenseTracker.DTOs.User.UserProfileResponse;
import org.jspecify.annotations.Nullable;

public interface UserService {
    UserProfileResponse getProfile(Long userId);

    UserProfileResponse updateUserProfile(Long userId, UpdateUserRequest request);

    void deleteUserProfile(Long userId);
}
