package com.K955.ExpenseTracker.Service.Impl;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseRequest;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseSummaryResponse;
import com.K955.ExpenseTracker.Entity.Expense;
import com.K955.ExpenseTracker.Entity.User;
import com.K955.ExpenseTracker.Enums.Expense.Category;
import com.K955.ExpenseTracker.Errors.BadRequestException;
import com.K955.ExpenseTracker.Errors.ResourceNotFoundException;
import com.K955.ExpenseTracker.Mapper.ExpenseMapper;
import com.K955.ExpenseTracker.Repository.ExpenseRepository;
import com.K955.ExpenseTracker.Repository.UserRepository;
import com.K955.ExpenseTracker.Service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseResponse createExpense(Long userId, ExpenseRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Expense expense = Expense.builder()
                .name(request.name())
                .amount(request.amount())
                .user(user)
                .category(request.category())
                .build();
        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponseFromExpense(expense);
    }

    @Override
    public ExpenseResponse getExpenseById(Long userId, Long expenseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new RuntimeException("Access Denied");
        }
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", expenseId.toString()));
        if(!expense.getUser().getId().equals(userId)) {
            throw new RuntimeException("Access Denied");
        }
        return expenseMapper.toExpenseResponseFromExpense(expense);
    }

    @Override
    public List<ExpenseSummaryResponse> getAllExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new RuntimeException("Access Denied");
        }
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findAllExpenses(userId));
    }

    @Override
    public ExpenseResponse updateExpense(Long userId, Long expenseId, ExpenseRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new RuntimeException("Access Denied");
        }
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", expenseId.toString()));
        if(!expense.getUser().getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        expense.setName(request.name());
        expense.setAmount(request.amount());
        expense.setCategory(request.category());
        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponseFromExpense(expense);
    }

    @Override
    public void deleteExpense(Long userId, Long expenseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", expenseId.toString()));
        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseSummaryResponse> getTravelExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Category category = Category.TRAVEL;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getShoppingExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Category category = Category.SHOPPING;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getMedicalExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Category category = Category.MEDICAL;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getFoodExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Category category = Category.FOOD;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getEducationExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Category category = Category.EDUCATION;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getHousingExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Category category = Category.HOUSING;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getEntertainmentExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        if(!user.getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        Category category = Category.ENTERTAINMENT;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }
}
