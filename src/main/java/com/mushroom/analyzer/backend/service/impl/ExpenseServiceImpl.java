package com.mushroom.analyzer.backend.service.impl;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.dto.req.ExpenseReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ExpenseResDto;
import com.mushroom.analyzer.backend.model.dto.res.ExpenseSummaryDto;
import com.mushroom.analyzer.backend.model.entity.*;
import com.mushroom.analyzer.backend.model.repository.ExpenseRepository;
import com.mushroom.analyzer.backend.service.ExpenseService;
import com.mushroom.analyzer.backend.service.PotStockService;
import com.mushroom.analyzer.backend.service.SaleService;
import com.mushroom.analyzer.backend.utils.enums.ExpenseType;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ModelMapper modelMapper;
    private final PotStockService potStockService;
    private final SaleService saleService;
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ModelMapper modelMapper, PotStockService potStockService, SaleService saleService, ExpenseRepository expenseRepository) {
        this.modelMapper = modelMapper;
        this.potStockService = potStockService;
        this.saleService = saleService;
        this.expenseRepository = expenseRepository;
    }

//    @Override
//    @Transactional
//    public ExpenseResDto addCapitalExpense(long potStockId, ExpenseReqDto expenseReqDto) throws SWException {
//        log.debug("addCapitalExpense method started");
//        PotStock potStock = potStockService.getPotStockById(potStockId);
//        Expense expense = new Expense();
//        expense.setType(ExpenseType.CAPITAL);
//        potStock.getExpenses().add(mapBasicExpenseAttributes(expense, expenseReqDto));
//        potStockService.savePotStock(potStock);
//
//        return modelMapper.map(expense, ExpenseResDto.class);
//    }

    @Override
    @Transactional
    public ExpenseResDto addExpense(long potStockId, ExpenseReqDto expenseReqDto) throws SWException {
        log.debug("addExpense method started");
        PotStock potStock = potStockService.getPotStockById(potStockId);
        Expense expense = new Expense();
        expense.setType(expenseReqDto.getExpenseType());
        potStock.getExpenses().add(mapBasicExpenseAttributes(expense, expenseReqDto));
        potStockService.savePotStock(potStock);

        return modelMapper.map(expense, ExpenseResDto.class);
    }

    @Override
    @Transactional
    public ExpenseResDto addOperationalExpense(long salesId, ExpenseReqDto expenseReqDto) throws SWException {
        log.debug("addOperationalExpense method started");
        Sale sale = saleService.getSaleById(salesId);
        Expense expense = new Expense();
        expense.setType(ExpenseType.OPERATIONAL);
        sale.setExpense(mapBasicExpenseAttributes(expense, expenseReqDto));
        saleService.saveSale(sale);

        return modelMapper.map(expense, ExpenseResDto.class);
    }

    @Override
    public List<ExpenseResDto> getAllExpenses() {
        log.debug("getAllExpenses method started");
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .map(expense -> modelMapper.map(expense, ExpenseResDto.class)).toList();
    }

    @Override
    @Transactional
    public ExpenseResDto getExpense(long id) throws SWException {
        log.debug("getExpense method started");
        return modelMapper.map(getExpenseById(id), ExpenseResDto.class);
    }

    private Expense getExpenseById(long id) throws SWException {
        log.debug("getExpenseById method started");
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isEmpty()){
            throw new SWException(
                    HttpStatus.BAD_REQUEST,
                    SWExceptionCode.MAEX001,
                    "Expense not found.",
                    "Expense not found with id: " + id
            );
        }
        return expense.get();
    }

    @Override
    public ExpenseResDto updateExpense(long id, ExpenseReqDto expenseReqDto) throws SWException {
        log.debug("updateExpense method started");
        Expense expenseToUpdate = getExpenseById(id);
        Expense updatedExpense = expenseRepository.save(mapBasicExpenseAttributes(expenseToUpdate, expenseReqDto));

        return modelMapper.map(updatedExpense, ExpenseResDto.class);
    }

    @Override
    public ExpenseResDto deleteExpense(long id) throws SWException {
        log.debug("deleteExpense method started");
        Expense expense = getExpenseById(id);
        expense.setDeleted(true);
        expense = expenseRepository.save(expense);
        log.debug("deleteExpense method finished");
        return modelMapper.map(expense, ExpenseResDto.class);
    }

    @Override
    public ExpenseSummaryDto getExpenseSummary(long potStockId) {
        List<Expense> expenses = expenseRepository.findAll();
        double totalExpense = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        return new ExpenseSummaryDto(totalExpense);
    }

    @Override
    @Transactional
    public List<ExpenseResDto> getAllExpensesForPotStock(long potStockId) throws SWException {
        log.debug("getAllExpensesForPotStock method started");
        PotStock potStock = potStockService.getPotStockById(potStockId);
        List<Expense> expenses = potStock.getExpenses();
        return expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .map(expense -> modelMapper.map(expense, ExpenseResDto.class)).toList();
    }

    private Expense mapBasicExpenseAttributes(Expense expense, ExpenseReqDto expenseReqDto) {
        expense.setDate(expenseReqDto.getDate());
        expense.setDescription(expenseReqDto.getDescription());
        expense.setAmount(expenseReqDto.getAmount());

        return expense;
    }
}
