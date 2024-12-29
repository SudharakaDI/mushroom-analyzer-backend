package com.mushroom.analyzer.backend.service.impl;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.dto.req.ProductionReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ProductionResDto;
import com.mushroom.analyzer.backend.model.entity.PotStock;
import com.mushroom.analyzer.backend.model.entity.Production;
import com.mushroom.analyzer.backend.model.repository.ProductionRepository;
import com.mushroom.analyzer.backend.service.PotStockService;
import com.mushroom.analyzer.backend.service.ProductionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final ModelMapper modelMapper;
    private final PotStockService potStockService;

    public ProductionServiceImpl(ProductionRepository productionRepository, ModelMapper modelMapper, PotStockService potStockService) {
        this.productionRepository = productionRepository;
        this.modelMapper = modelMapper;
        this.potStockService = potStockService;
    }

    @Override
    public ProductionResDto addProduction(long potStockId, ProductionReqDto productionReqDto) throws SWException {
        log.debug("addProduction method started");
        PotStock potStock = potStockService.getPotStockById(potStockId);
        Production production = modelMapper.map(productionReqDto, Production.class);
        potStock.getProductions().add(production);
        potStockService.savePotStock(potStock);
        return modelMapper.map(production, ProductionResDto.class);
    }

    @Override
    @Transactional
    public List<ProductionResDto> getAllProductions() {
        List<Production> productions = productionRepository.findAll();
        return productions.stream()
                .map(production -> modelMapper.map(production, ProductionResDto.class)).toList();
    }

    @Override
    @Transactional
    public ProductionResDto getProduction(long id) throws SWException {
        return modelMapper.map(getProductionById(id), ProductionResDto.class);
    }

    @Override
    @Transactional
    public ProductionResDto updateProduction(long id, ProductionReqDto productionReqDto) throws SWException {
        Production productionToUpdate = getProductionById(id);
        productionToUpdate.setProductionDate(productionReqDto.getProductionDate());
        productionToUpdate.setMushroomType(productionReqDto.getMushroomType());
        productionToUpdate.setNumberOfItems(productionReqDto.getNumberOfItems());
        productionToUpdate.setPacketPrice(productionReqDto.getPacketPrice());
        productionToUpdate.setPacketWeight(productionReqDto.getPacketWeight());

        Production updatedProduction = productionRepository.save(productionToUpdate);

        return modelMapper.map(updatedProduction, ProductionResDto.class);
    }

    @Override
    public ProductionResDto deleteProduction(long id) throws SWException {
        log.debug("deleteProduction method started");
        Production production = getProductionById(id);
        production.setDeleted(true);
        production = productionRepository.save(production);
        log.debug("deleteProduction method finished");
        return modelMapper.map(production, ProductionResDto.class);
    }

    private Production getProductionById(long id) throws SWException {
        Optional<Production> production = productionRepository.findById(id);
        if(production.isEmpty()){
            throw new SWException(
                    HttpStatus.BAD_REQUEST,
                    SWExceptionCode.MAPR001,
                    "Production not found.",
                    "Production not found with id: " + id
            );
        }
        return production.get();
    }

}
