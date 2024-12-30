package com.mushroom.analyzer.backend.service.impl;


import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.dto.req.SalesReqDto;
import com.mushroom.analyzer.backend.model.dto.res.SalesResDto;
import com.mushroom.analyzer.backend.model.entity.Production;
import com.mushroom.analyzer.backend.model.entity.Sale;
import com.mushroom.analyzer.backend.model.repository.SaleRepository;
import com.mushroom.analyzer.backend.service.ProductionService;
import com.mushroom.analyzer.backend.service.SaleService;
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
        log.debug("getAllSales method started");
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(sale -> modelMapper.map(sale, SalesResDto.class)).toList();
    }

    @Override
    public SalesResDto getSale(long id) throws SWException {
        log.debug("getSale method started");
        return modelMapper.map(getSaleById(id), SalesResDto.class);
    }

    @Override
    @Transactional
    public SalesResDto updateSale(long id, SalesReqDto salesReqDto) throws SWException {
        log.debug("updateSale method started");
        Sale saleToUpdate = getSaleById(id);
        saleToUpdate.setNumberOfItems(salesReqDto.getNumberOfItems());
        saleToUpdate.setSeller(stakeHolderService.getStakeHolderById(salesReqDto.getStakeHolderId()));
        Sale updatedSale = saleRepository.save(saleToUpdate);

        return modelMapper.map(updatedSale, SalesResDto.class);
    }

    @Override
    public SalesResDto deleteSale(long id) throws SWException {
        log.debug("deleteSale method started");
        Sale sale = getSaleById(id);
        sale.setDeleted(true);
        sale = saleRepository.save(sale);
        log.debug("deleteSale method finished");
        return modelMapper.map(sale, SalesResDto.class);
    }

    public Sale getSaleById(long id) throws SWException {
        Optional<Sale> sale = saleRepository.findById(id);
        if(sale.isEmpty()){
            throw new SWException(
                    HttpStatus.BAD_REQUEST,
                    SWExceptionCode.MAPR001,
                    "Sale not found.",
                    "Sale not found with id: " + id
            );
        }
        return sale.get();
    }
}
