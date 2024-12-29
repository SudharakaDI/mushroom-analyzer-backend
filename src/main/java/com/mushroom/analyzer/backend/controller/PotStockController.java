package com.mushroom.analyzer.backend.controller;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.PotStockReqDto;
import com.mushroom.analyzer.backend.model.dto.res.PotStockResDto;
import com.mushroom.analyzer.backend.service.PotStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "${base-url.context}/pot-stock")
@CrossOrigin(origins = "*")
public class PotStockController {
    private final PotStockService potStockService;

    public PotStockController(PotStockService potStockService) {
        this.potStockService = potStockService;
    }

    @GetMapping
    public ResponseEntity<List<PotStockResDto>> getAllPotStock(){
        log.info("Received request to get all pot stocks");
        return new ResponseEntity<>(potStockService.getAllPotStocks(), HttpStatus.OK);
    }

    @GetMapping(value = "/{potStockId}")
    public ResponseEntity<PotStockResDto> getPotStock(@PathVariable long potStockId) throws SWException {
        log.info("Received request to get pot stock with id: {}", potStockId);
        return new ResponseEntity<>( potStockService.getPotStock(potStockId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PotStockResDto> addPotStock(@RequestBody PotStockReqDto potStockReqDto) throws SWException {
        log.info("Received request to add pot stock");
        return new ResponseEntity<>( potStockService.addPotStock(potStockReqDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{potStockId}")
    public ResponseEntity<PotStockResDto> updatePotStock(@PathVariable(value = "potStockId") long id, @RequestBody PotStockReqDto potStockReqDto) throws SWException {
        log.info("Received request to update pot stock details");
        return new ResponseEntity<>(potStockService.updatePotStock(id, potStockReqDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{potStockId}")
    public ResponseEntity<PotStockResDto> removePotStock(@PathVariable(value = "potStockId") long id) throws SWException {
        log.info("Received request to delete pot stock");
        return new ResponseEntity<>(potStockService.deletePotStock(id), HttpStatus.OK);
    }

}
