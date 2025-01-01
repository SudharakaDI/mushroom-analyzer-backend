package com.mushroom.analyzer.backend.controller;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.IncomeReqDto;
import com.mushroom.analyzer.backend.model.dto.res.IncomeResDto;
import com.mushroom.analyzer.backend.service.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "${base-url.context}/income")
@CrossOrigin(origins = "*")
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public ResponseEntity<List<IncomeResDto>> getAllIncomes(){
        log.info("Received request to get all incomes");
        return new ResponseEntity<>(incomeService.getAllIncomes(), HttpStatus.OK);
    }

    @GetMapping(value = "/{incomeId}")
    public ResponseEntity<IncomeResDto> getIncome(@PathVariable long incomeId) throws SWException {
        log.info("Received request to get income with id: {}", incomeId);
        return new ResponseEntity<>( incomeService.getIncome(incomeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IncomeResDto> addIncome(@RequestParam long salesId, @RequestBody IncomeReqDto incomeReqDto) throws SWException {
        log.info("Received request to add income");
        return new ResponseEntity<>( incomeService.addIncome(salesId, incomeReqDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{incomeId}")
    public ResponseEntity<IncomeResDto> updateIncome(@PathVariable(value = "incomeId") long id, @RequestBody IncomeReqDto incomeReqDto) throws SWException {
        log.info("Received request to update income details with id: {}", id);
        return new ResponseEntity<>(incomeService.updateIncome(id, incomeReqDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{incomeId}")
    public ResponseEntity<IncomeResDto> removeIncome(@PathVariable(value = "incomeId") long id) throws SWException {
        log.info("Received request to delete income with id: {}", id);
        return new ResponseEntity<>(incomeService.deleteIncome(id), HttpStatus.OK);
    }

}
