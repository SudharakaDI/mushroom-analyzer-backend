package com.mushroom.analyzer.backend.service;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.ProductionReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ProductionResDto;

import java.util.List;

public interface ProductionService {
    ProductionResDto addProduction(long potStockId, ProductionReqDto productionReqDto) throws SWException;
    List<ProductionResDto> getAllProductions();
    ProductionResDto getProduction(long id) throws SWException;
    ProductionResDto updateProduction(long id, ProductionReqDto productionReqDto) throws SWException;
    ProductionResDto deleteProduction(long id) throws SWException;
}
