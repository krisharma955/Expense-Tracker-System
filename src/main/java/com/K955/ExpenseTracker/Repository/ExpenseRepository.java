package com.K955.ExpenseTracker.Repository;

import com.K955.ExpenseTracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
            SELECT e FROM Expense e
            WHERE e.deletedAt IS NULL
            ORDER BY e.updatedAt DESC
            """
    )
    List<Expense> findAllExpenses(@Param("userId") Long userId);

}
