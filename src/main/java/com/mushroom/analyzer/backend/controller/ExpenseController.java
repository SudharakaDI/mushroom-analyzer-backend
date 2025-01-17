package com.mushroom.analyzer.backend.controller;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.ExpenseReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ExpenseResDto;
import com.mushroom.analyzer.backend.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "${base-url.context}/expense")
@CrossOrigin(origins = "*")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResDto>> getAllExpenses(){
        log.info("Received request to get all expenses");
        return new ResponseEntity<>(expenseService.getAllExpenses(), HttpStatus.OK);
    }

    @GetMapping(value = "/{expenseId}")
    public ResponseEntity<ExpenseResDto> getExpense(@PathVariable long expenseId) throws SWException {
        log.info("Received request to get expense with id: {}", expenseId);
        return new ResponseEntity<>( expenseService.getExpense(expenseId), HttpStatus.OK);
    }

    @PostMapping(value = "/capital/{potStockId}")
    public ResponseEntity<ExpenseResDto> addCapitalExpense(@PathVariable long potStockId, @RequestBody ExpenseReqDto expenseReqDto) throws SWException {
        log.info("Received request to add capital expense");
        return new ResponseEntity<>( expenseService.addCapitalExpense(potStockId, expenseReqDto), HttpStatus.OK);
    }

    @PostMapping(value = "/operation/{salesId}")
    public ResponseEntity<ExpenseResDto> addOperationalExpense(@PathVariable long salesId, @RequestBody ExpenseReqDto expenseReqDto) throws SWException {
        log.info("Received request to add operational expense");
        return new ResponseEntity<>( expenseService.addOperationalExpense(salesId, expenseReqDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{expenseId}")
    public ResponseEntity<ExpenseResDto> updateIncome(@PathVariable(value = "expenseId") long id, @RequestBody ExpenseReqDto expenseReqDto) throws SWException {
        log.info("Received request to update expense details with id: {}", id);
        return new ResponseEntity<>(expenseService.updateExpense(id, expenseReqDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{expenseId}")
    public ResponseEntity<ExpenseResDto> removeExpense(@PathVariable(value = "expenseId") long id) throws SWException {
        log.info("Received request to delete expense with id: {}", id);
        return new ResponseEntity<>(expenseService.deleteExpense(id), HttpStatus.OK);
    }

}
