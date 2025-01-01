package com.mushroom.analyzer.backend.controller;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.SalesReqDto;
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

    @GetMapping(value = "/{salesId}")
    public ResponseEntity<SalesResDto> getSale(@PathVariable long salesId) throws SWException {
        log.info("Received request to get sales with id: {}", salesId);
        return new ResponseEntity<>( saleService.getSale(salesId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalesResDto> addSales(@RequestParam long salesId, @RequestBody SalesReqDto salesReqDto) throws SWException {
        log.info("Received request to add production");
        return new ResponseEntity<>( saleService.addSales(salesId, salesReqDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{salesId}")
    public ResponseEntity<SalesResDto> updateSales(@PathVariable(value = "salesId") long id, @RequestBody SalesReqDto salesReqDto) throws SWException {
        log.info("Received request to update sale details with id: {}", id);
        return new ResponseEntity<>(saleService.updateSale(id, salesReqDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{salesId}")
    public ResponseEntity<SalesResDto> removeSale(@PathVariable(value = "salesId") long id) throws SWException {
        log.info("Received request to delete sales with id: {}", id);
        return new ResponseEntity<>(saleService.deleteSale(id), HttpStatus.OK);
    }

}
