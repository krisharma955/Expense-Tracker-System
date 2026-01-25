package com.K955.ExpenseTracker.Errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String resourceId;
}
