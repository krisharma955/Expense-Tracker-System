package com.K955.ExpenseTracker.Service.Impl;

import com.K955.ExpenseTracker.DTOs.Expense.*;
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", expenseId.toString()));
        if(!expense.getUser().getId().equals(userId)) {
            throw new BadRequestException("Access Denied");
        }
        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseSummaryResponse> findExpensesAfter(Long userId, DateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));

        LocalDate date = LocalDate.of(request.year(), request.month(), request.day());
        Instant instant = date
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();

        if(instant.isAfter(Instant.now())) {
            throw new BadRequestException("Inappropriate Request");
        }
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findByUserIdAndCreatedAtAfter(userId, instant));
    }

    @Override
    public List<ExpenseSummaryResponse> getExpensesBetween(Long userId, DurationRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));

        LocalDate startDate = LocalDate.of(request.startYear(), request.startMonth(), request.startDay());
        LocalDate endDate = LocalDate.of(request.endYear(), request.endMonth(), request.endDay());

        Instant start = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = endDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return expenseMapper.toListOfExpenseSummaryResponse(
                expenseRepository.findByUserIdAndCreatedAtBetween(userId, start, end));
    }

    @Override
    public List<ExpenseSummaryResponse> getTravelExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Category category = Category.TRAVEL;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getShoppingExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Category category = Category.SHOPPING;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getMedicalExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Category category = Category.MEDICAL;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getFoodExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Category category = Category.FOOD;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getEducationExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Category category = Category.EDUCATION;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getHousingExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Category category = Category.HOUSING;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }

    @Override
    public List<ExpenseSummaryResponse> getEntertainmentExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Category category = Category.ENTERTAINMENT;
        return expenseMapper.toListOfExpenseSummaryResponse(expenseRepository.findTravelExpenses(userId, category));
    }
}
