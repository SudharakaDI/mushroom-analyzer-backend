package com.mushroom.analyzer.backend.service;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.ExpenseReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ExpenseResDto;
import com.mushroom.analyzer.backend.model.dto.res.ExpenseSummaryDto;

import java.util.List;

public interface ExpenseService {
    ExpenseResDto addCapitalExpense(long potStockId, ExpenseReqDto expenseReqDto) throws SWException;
    ExpenseResDto addOperationalExpense(long salesId, ExpenseReqDto expenseReqDto) throws SWException;
    List<ExpenseResDto> getAllExpenses();
    ExpenseResDto getExpense(long id) throws SWException;
    ExpenseResDto updateExpense(long id, ExpenseReqDto expenseReqDto) throws SWException;
    ExpenseResDto deleteExpense(long id) throws SWException;
    ExpenseSummaryDto getExpenseSummary(long potStockId);
}
