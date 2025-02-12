package com.mushroom.analyzer.backend.service;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.IncomeReqDto;
import com.mushroom.analyzer.backend.model.dto.res.IncomeResDto;
import com.mushroom.analyzer.backend.model.dto.res.IncomeSummaryDto;
import java.util.List;

public interface IncomeService {
//    IncomeResDto addIncome(long saleId, IncomeReqDto salesReqDto) throws SWException;
    IncomeResDto addIncome(long potStockId, IncomeReqDto salesReqDto) throws SWException;
    List<IncomeResDto> getAllIncomes();
    IncomeResDto getIncome(long id) throws SWException;
    IncomeResDto updateIncome(long id, IncomeReqDto incomeReqDto) throws SWException;
    IncomeResDto deleteIncome(long id) throws SWException;
    IncomeSummaryDto getIncomeSummary(long potStockId);
    List<IncomeResDto> getAllIncomesForPotStock(long potStockId) throws SWException;
}
