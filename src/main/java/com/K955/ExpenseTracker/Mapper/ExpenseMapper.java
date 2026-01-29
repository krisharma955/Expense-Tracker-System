package com.K955.ExpenseTracker.Mapper;

import com.K955.ExpenseTracker.DTOs.Expense.ExpenseSummaryResponse;
import com.K955.ExpenseTracker.DTOs.Expense.ExpenseResponse;
import com.K955.ExpenseTracker.Entity.Expense;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseResponse toExpenseResponseFromExpense(Expense expense);

    List<ExpenseSummaryResponse> toListOfExpenseSummaryResponse(List<Expense> expenseList);

}
