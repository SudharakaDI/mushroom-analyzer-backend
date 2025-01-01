package com.mushroom.analyzer.backend.service;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.IncomeReqDto;
import com.mushroom.analyzer.backend.model.dto.res.IncomeResDto;


import java.util.List;

public interface IncomeService {
    IncomeResDto addIncome(long saleId, IncomeReqDto salesReqDto) throws SWException;
    List<IncomeResDto> getAllIncomes();
    IncomeResDto getIncome(long id) throws SWException;
    IncomeResDto updateIncome(long id, IncomeReqDto incomeReqDto) throws SWException;
    IncomeResDto deleteIncome(long id) throws SWException;
}
