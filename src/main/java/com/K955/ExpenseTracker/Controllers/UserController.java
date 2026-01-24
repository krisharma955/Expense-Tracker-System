package com.K955.ExpenseTracker.Controllers;

import com.K955.ExpenseTracker.DTOs.User.UpdateUserRequest;
import com.K955.ExpenseTracker.DTOs.User.UserProfileResponse;
import com.K955.ExpenseTracker.Security.JwtService;
import com.K955.ExpenseTracker.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PatchMapping("/update-user")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody UpdateUserRequest request) {
        Long userId = jwtService.getCurrentUserId();
        return ResponseEntity.ok(userService.updateUserProfile(userId, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserProfile() {
        Long userId = jwtService.getCurrentUserId();
        userService.deleteUserProfile(userId);
        return ResponseEntity.noContent().build();
    }

}
