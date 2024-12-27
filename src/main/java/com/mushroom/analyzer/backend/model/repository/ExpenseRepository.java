package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
