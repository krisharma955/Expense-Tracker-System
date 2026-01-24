package com.K955.ExpenseTracker.Mapper;

import com.K955.ExpenseTracker.DTOs.User.UserProfileResponse;
import com.K955.ExpenseTracker.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfileResponse toUserProfileResponseFromUser(User user);

}
