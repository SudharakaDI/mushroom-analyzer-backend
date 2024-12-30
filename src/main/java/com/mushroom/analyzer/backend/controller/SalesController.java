package com.mushroom.analyzer.backend.controller;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.ProductionReqDto;
import com.mushroom.analyzer.backend.model.dto.req.SalesReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ProductionResDto;
import com.mushroom.analyzer.backend.model.dto.res.SalesResDto;
import com.mushroom.analyzer.backend.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "${base-url.context}/sales")
@CrossOrigin(origins = "*")
public class SalesController {
    private final SaleService saleService;

    public SalesController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SalesResDto>> getAllSales(){
        log.info("Received request to get all sales");
        return new ResponseEntity<>(saleService.getAllSales(), HttpStatus.OK);
    }

//    @GetMapping(value = "/{productionId}")
//    public ResponseEntity<ProductionResDto> getProduction(@PathVariable long productionId) throws SWException {
//        log.info("Received request to get production with id: {}", productionId);
//        return new ResponseEntity<>( saleService.getProduction(productionId), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<SalesResDto> addSales(@RequestParam long productionId, @RequestBody SalesReqDto salesReqDto) throws SWException {
        log.info("Received request to add production");
        return new ResponseEntity<>( saleService.addSales(productionId, salesReqDto), HttpStatus.OK);
    }

//    @PutMapping(value = "/{productionId}")
//    public ResponseEntity<ProductionResDto> updateProduction(@PathVariable(value = "productionId") long id, @RequestBody ProductionReqDto productionReqDto) throws SWException {
//        log.info("Received request to update production details with id: {}", id);
//        return new ResponseEntity<>(saleService.updateProduction(id, productionReqDto), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/{productionId}")
//    public ResponseEntity<ProductionResDto> removeProduction(@PathVariable(value = "productionId") long id) throws SWException {
//        log.info("Received request to delete production with id: {}", id);
//        return new ResponseEntity<>(saleService.deleteProduction(id), HttpStatus.OK);
//    }

}
