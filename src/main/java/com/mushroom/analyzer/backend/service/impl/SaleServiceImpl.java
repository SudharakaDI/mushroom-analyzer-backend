package com.mushroom.analyzer.backend.service.impl;


import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.SalesReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ProductionResDto;
import com.mushroom.analyzer.backend.model.dto.res.SalesResDto;
import com.mushroom.analyzer.backend.model.entity.PotStock;
import com.mushroom.analyzer.backend.model.entity.Production;
import com.mushroom.analyzer.backend.model.entity.Sale;
import com.mushroom.analyzer.backend.model.repository.SaleRepository;
import com.mushroom.analyzer.backend.service.ProductionService;
import com.mushroom.analyzer.backend.service.SaleService;
import com.mushroom.analyzer.backend.service.StakeHolderService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SaleServiceImpl implements SaleService {

    private final ModelMapper modelMapper;
    private final ProductionService productionService;
    private final StakeHolderService stakeHolderService;
    private final SaleRepository saleRepository;

    public SaleServiceImpl(ModelMapper modelMapper, ProductionService productionService, StakeHolderService stakeHolderService, SaleRepository saleRepository) {
        this.modelMapper = modelMapper;
        this.productionService = productionService;
        this.stakeHolderService = stakeHolderService;
        this.saleRepository = saleRepository;
    }


    @Override
    @Transactional
    public SalesResDto addSales(long productionId, SalesReqDto salesReqDto) throws SWException {
        log.debug("addSales method started");
        Production production = productionService.getProductionById(productionId);
        Sale sale = new Sale();
        sale.setNumberOfItems(salesReqDto.getNumberOfItems());
        sale.setSeller(stakeHolderService.getStakeHolderById(salesReqDto.getStakeHolderId()));
        production.getSales().add(sale);
        productionService.saveProduction(production);
        return modelMapper.map(sale, SalesResDto.class);
    }

    @Override
    public List<SalesResDto> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(sale -> modelMapper.map(sale, SalesResDto.class)).toList();
    }

    @Override
    public SalesResDto getSale(long id) throws SWException {
        return null;
    }

    @Override
    public SalesResDto updateSale(long id, SalesReqDto salesReqDto) throws SWException {
        return null;
    }

    @Override
    public SalesResDto deleteSale(long id) throws SWException {
        return null;
    }
}
