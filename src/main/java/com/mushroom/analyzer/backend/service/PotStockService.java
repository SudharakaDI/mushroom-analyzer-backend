package com.mushroom.analyzer.backend.service;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.PotStockReqDto;
import com.mushroom.analyzer.backend.model.dto.res.PotStockResDto;

import java.util.List;

public interface PotStockService {
    PotStockResDto addPotStock(PotStockReqDto potStockReqDto) throws SWException;
    List<PotStockResDto> getAllPotStocks();
    PotStockResDto getPotStock(long id) throws SWException;
    PotStockResDto updatePotStock(long id, PotStockReqDto potStockReqDto) throws SWException;
    PotStockResDto deletePotStock(long id) throws SWException;
}
