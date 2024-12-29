package com.mushroom.analyzer.backend.service.impl;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.dto.req.PotStockReqDto;
import com.mushroom.analyzer.backend.model.dto.res.PotStockResDto;
import com.mushroom.analyzer.backend.model.entity.PotStock;
import com.mushroom.analyzer.backend.model.entity.StakeHolder;
import com.mushroom.analyzer.backend.model.repository.PotStockRepository;
import com.mushroom.analyzer.backend.service.PotStockService;
import com.mushroom.analyzer.backend.service.StakeHolderService;
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
    public List<PotStockResDto> getAllPotStocks() {
        List<PotStock> potStocks = potStockRepository.findAll();
        return potStocks.stream()
                .map(potStock -> modelMapper.map(potStock, PotStockResDto.class)).toList();

    }

    @Override
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
}
