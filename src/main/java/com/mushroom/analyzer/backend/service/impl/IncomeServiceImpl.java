package com.mushroom.analyzer.backend.service.impl;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.dto.req.IncomeReqDto;
import com.mushroom.analyzer.backend.model.dto.res.IncomeResDto;
import com.mushroom.analyzer.backend.model.entity.Income;
import com.mushroom.analyzer.backend.model.entity.Sale;
import com.mushroom.analyzer.backend.model.repository.IncomeRepository;
import com.mushroom.analyzer.backend.service.IncomeService;
import com.mushroom.analyzer.backend.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {

    private final ModelMapper modelMapper;
    private final IncomeRepository incomeRepository;
    private final SaleService saleService;

    public IncomeServiceImpl(ModelMapper modelMapper, IncomeRepository incomeRepository, SaleService saleService) {
        this.modelMapper = modelMapper;
        this.incomeRepository = incomeRepository;
        this.saleService = saleService;
    }

    @Override
    @Transactional
    public IncomeResDto addIncome(long saleId, IncomeReqDto incomeReqDto) throws SWException {
        log.debug("addIncome method started");
        Sale sale = saleService.getSaleById(saleId);
        Income income = new Income();
        income.setAmount(incomeReqDto.getAmount());
        income.setDescription(incomeReqDto.getDescription());
        income.setDate(incomeReqDto.getDate());
        sale.setIncome(income);
        saleService.saveSale(sale);
        return modelMapper.map(income, IncomeResDto.class);
    }

    @Override
    public List<IncomeResDto> getAllIncomes() {
        log.debug("getAllIncomes method started");
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream()
                .map(income -> modelMapper.map(income, IncomeResDto.class)).toList();
    }

    @Override
    public IncomeResDto getIncome(long id) throws SWException {
        log.debug("getIncome method started");
        return modelMapper.map(getIncomeById(id), IncomeResDto.class);
    }

    private Income getIncomeById(long id) throws SWException {
        log.debug("getIncomeById method started");
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isEmpty()){
            throw new SWException(
                    HttpStatus.BAD_REQUEST,
                    SWExceptionCode.MAIC001,
                    "Income not found.",
                    "Income not found with id: " + id
            );
        }
        return income.get();
    }

    @Override
    public IncomeResDto updateIncome(long id, IncomeReqDto incomeReqDto) throws SWException {
        log.debug("updateIncome method started");
        Income incomeToUpdate = getIncomeById(id);
        incomeToUpdate.setDate(incomeReqDto.getDate());
        incomeToUpdate.setDescription(incomeReqDto.getDescription());
        incomeToUpdate.setAmount(incomeReqDto.getAmount());
        Income updatedIncome = incomeRepository.save(incomeToUpdate);

        return modelMapper.map(updatedIncome, IncomeResDto.class);
    }

    @Override
    public IncomeResDto deleteIncome(long id) throws SWException {
        log.debug("deleteIncome method started");
        Income income = getIncomeById(id);
        income.setDeleted(true);
        income = incomeRepository.save(income);
        log.debug("deleteIncome method finished");
        return modelMapper.map(income, IncomeResDto.class);
    }
}
