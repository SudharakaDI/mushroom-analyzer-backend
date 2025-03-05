package com.mushroom.analyzer.backend.service.impl;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.dto.req.IncomeReqDto;
import com.mushroom.analyzer.backend.model.dto.res.IncomeResDto;
import com.mushroom.analyzer.backend.model.dto.res.IncomeSummaryDto;
import com.mushroom.analyzer.backend.model.dto.res.PotStockMinimalResDto;
import com.mushroom.analyzer.backend.model.entity.Income;
import com.mushroom.analyzer.backend.model.entity.PotStock;
import com.mushroom.analyzer.backend.model.repository.IncomeRepository;
import com.mushroom.analyzer.backend.service.IncomeService;
import com.mushroom.analyzer.backend.service.PotStockService;
import com.mushroom.analyzer.backend.service.ProductionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {

    private final ModelMapper modelMapper;
    private final IncomeRepository incomeRepository;
    private final PotStockService potStockService;
    private final ProductionService productionService;

    public IncomeServiceImpl(ModelMapper modelMapper, IncomeRepository incomeRepository, PotStockService potStockService, ProductionService productionService) {
        this.modelMapper = modelMapper;
        this.incomeRepository = incomeRepository;
        this.potStockService = potStockService;
        this.productionService = productionService;
    }

    @Override
    @Transactional
    public List<IncomeResDto> addIncome(long potStockId, IncomeReqDto incomeReqDto) throws SWException {
        log.debug("addIncome method started");
        List<Income> addedIncomes = new ArrayList<>();
        int totalDayProduction = productionService.getProductionCountForDate(incomeReqDto.getDate());
        List<PotStockMinimalResDto> potStocks = potStockService.getAllPotStocksMinimal();

        for(PotStockMinimalResDto potStockMinimalResDto:potStocks){
            PotStock potStock = potStockService.getPotStockById(potStockMinimalResDto.getId());
            Income income = new Income();
            double incomeAmount = (double) potStockService.getProductionCountByPotStockAndDate(potStock.getId(), incomeReqDto.getDate()) /totalDayProduction * incomeReqDto.getAmount();
            income.setAmount(Math.round(incomeAmount*100.0)/100.0);
            income.setDescription(incomeReqDto.getDescription());
            income.setDate(incomeReqDto.getDate());
            potStock.getIncomes().add(income);
            addedIncomes.add(income);
            potStockService.savePotStock(potStock);
        }

        return addedIncomes.stream().map(income -> modelMapper.map(income, IncomeResDto.class)).toList();

    }

    @Override
    public List<IncomeResDto> getAllIncomes() {
        log.debug("getAllIncomes method started");
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
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

    @Override
    public IncomeSummaryDto getIncomeSummary(long potStockId) {
        List<Income> incomes = incomeRepository.findAll();
        double totalIncome = incomes.stream()
                .mapToDouble(Income::getAmount)
                .sum();

        return new IncomeSummaryDto(totalIncome);
    }

    @Override
    @Transactional
    public List<IncomeResDto> getAllIncomesForPotStock(long potStockId) throws SWException {
        log.debug("getAllIncomesForPotStock method started");
        PotStock potStock = potStockService.getPotStockById(potStockId);
        List<Income> incomes = potStock.getIncomes();
        return incomes.stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(income -> modelMapper.map(income, IncomeResDto.class)).toList();
    }
}
