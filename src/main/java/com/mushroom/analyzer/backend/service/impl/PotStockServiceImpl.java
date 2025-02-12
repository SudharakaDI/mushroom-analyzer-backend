package com.mushroom.analyzer.backend.service.impl;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.dto.req.PotStockReqDto;
import com.mushroom.analyzer.backend.model.dto.res.PotStockMinimalResDto;
import com.mushroom.analyzer.backend.model.dto.res.PotStockResDto;
import com.mushroom.analyzer.backend.model.dto.res.PotStockSummaryResDto;
import com.mushroom.analyzer.backend.model.dto.res.ProductionResDto;
import com.mushroom.analyzer.backend.model.entity.*;
import com.mushroom.analyzer.backend.model.repository.PotStockRepository;
import com.mushroom.analyzer.backend.service.PotStockService;
import com.mushroom.analyzer.backend.service.StakeHolderService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PotStockServiceImpl implements PotStockService {

    private final PotStockRepository potStockRepository;
    private final StakeHolderService stakeHolderService;
    private final ModelMapper modelMapper;

    public PotStockServiceImpl(PotStockRepository potStockRepository, StakeHolderService stakeHolderService, ModelMapper modelMapper) {
        this.potStockRepository = potStockRepository;
        this.stakeHolderService = stakeHolderService;
        this.modelMapper = modelMapper;
    }

    @Override
    public PotStockResDto addPotStock(PotStockReqDto potStockReqDto) throws SWException {
        log.debug("addPotStock method started");
        PotStock potStock = potStockMapper(new PotStock(), potStockReqDto);
        PotStock addedPotStock = potStockRepository.save(potStock);
        return modelMapper.map(addedPotStock, PotStockResDto.class);
    }

    private PotStock potStockMapper(PotStock potStock, PotStockReqDto potStockReqDto) throws SWException {
        log.debug("potStockMapper method started");
        potStock.setMushroomType(potStockReqDto.getMushroomType());
        potStock.setNumberOfPots(potStockReqDto.getNumberOfPots());
        potStock.setDescription(potStockReqDto.getDescription());

        if(potStockReqDto.getStakeHolderId() != null) {
            StakeHolder stakeHolder = stakeHolderService.getStakeHolder(potStockReqDto.getStakeHolderId());
            potStock.setMushroomSupplier(stakeHolder);
        }

        return potStock;
    }

    @Override
    @Transactional
    public List<PotStockResDto> getAllPotStocks() {
        List<PotStock> potStocks = potStockRepository.findAll();
        return potStocks.stream()
                .map(potStock -> modelMapper.map(potStock, PotStockResDto.class)).toList();

    }

    @Override
    @Transactional
    public PotStockResDto getPotStock(long id) throws SWException {
     return modelMapper.map(getPotStockById(id), PotStockResDto.class);
    }

    @Override
    public PotStockResDto updatePotStock(long id, PotStockReqDto potStockReqDto) throws SWException {
        PotStock potStockToUpdate = getPotStockById(id);
        potStockToUpdate.setMushroomType(potStockReqDto.getMushroomType());
        potStockToUpdate.setNumberOfPots(potStockReqDto.getNumberOfPots());
        potStockToUpdate.setDescription(potStockReqDto.getDescription());

        PotStock updatedPotStock = potStockRepository.save(potStockToUpdate);

        return modelMapper.map(updatedPotStock, PotStockResDto.class);
    }

    @Override
    public PotStockResDto deletePotStock(long id) throws SWException {
        log.debug("deleteStory method started");
        PotStock potStockToDelete =  getPotStockById(id);
        potStockToDelete.setDeleted(true);
        potStockToDelete = potStockRepository.save(potStockToDelete);
        log.debug("deleteStory method finished");
        return modelMapper.map(potStockToDelete, PotStockResDto.class);
    }

    @Override
    public PotStock getPotStockById(long id) throws SWException {
        Optional<PotStock> potStock = potStockRepository.findById(id);
        if(potStock.isEmpty()){
            throw new SWException(
                    HttpStatus.BAD_REQUEST,
                    SWExceptionCode.MAPS001,
                    "Pot stock not found.",
                    "Pot stock  not found with id: " + id
            );
        }
        return potStock.get();
    }

    @Override
    public void savePotStock(PotStock potStock) {
        potStockRepository.save(potStock);
    }

    @Override
    public List<PotStockMinimalResDto> getAllPotStocksMinimal() {
        log.debug("getAllPotStocksMinimal method started");
        List<PotStock> potStocks = potStockRepository.findAll();
        return potStocks.stream()
                .map(potStock -> modelMapper.map(potStock, PotStockMinimalResDto.class)).toList();
    }

    @Override
    @Transactional
    public List<ProductionResDto> getProductionsByPotStock(long potStockId) throws SWException {
        log.debug("getProductionsByPotStock method started");
        PotStock potStock = getPotStockById(potStockId);
        return potStock.getProductions().stream().map(production -> modelMapper.map(production, ProductionResDto.class)).toList();
    }

    @Override
    @Transactional
    public PotStockSummaryResDto getPotStockSummary(long id) throws SWException {
        log.debug("getPotStockSummary method started");
        PotStock potStock = getPotStockById(id);
        List<Production> productions = potStock.getProductions();

        int totalProduction = productions.stream()
                .mapToInt(Production::getNumberOfItems)
                .sum();

        List<Sale> sales = potStock.getProductions().stream().flatMap(production -> production.getSales().stream()).toList();

        double totalIncome = sales.stream().mapToDouble(sale-> (sale.getIncome() != null) ? sale.getIncome().getAmount() : 0.0).sum();
        double totalSalesExpense = sales.stream().mapToDouble(sale-> (sale.getExpense() != null) ? sale.getExpense().getAmount() : 0.0).sum();
        double totalCapitalExpense = potStock.getExpenses().stream().mapToDouble(Expense::getAmount).sum();
        double totalExpense = totalCapitalExpense + totalSalesExpense;
        double totalProfit = totalIncome - totalExpense;

        return new PotStockSummaryResDto(totalProduction,totalIncome,totalExpense,totalProfit);
    }
}
