package com.mushroom.analyzer.backend.service;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.SalesReqDto;
import com.mushroom.analyzer.backend.model.dto.res.SalesResDto;
import com.mushroom.analyzer.backend.model.entity.Sale;

import java.util.List;

public interface SaleService {
    SalesResDto addSales(long productionId, SalesReqDto salesReqDto) throws SWException;
    List<SalesResDto> getAllSales();
    SalesResDto getSale(long id) throws SWException;
    SalesResDto updateSale(long id, SalesReqDto salesReqDto) throws SWException;
    SalesResDto deleteSale(long id) throws SWException;
    Sale getSaleById(long id) throws SWException;
    void saveSale(Sale sale);

}
