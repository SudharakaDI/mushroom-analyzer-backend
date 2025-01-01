package com.mushroom.analyzer.backend.controller;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.dto.req.ProductionReqDto;
import com.mushroom.analyzer.backend.model.dto.res.ProductionResDto;
import com.mushroom.analyzer.backend.service.ProductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "${base-url.context}/production")
@CrossOrigin(origins = "*")
public class ProductionController {
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public ResponseEntity<List<ProductionResDto>> getAllProductions(){
        log.info("Received request to get all productions");
        return new ResponseEntity<>(productionService.getAllProductions(), HttpStatus.OK);
    }

    @GetMapping(value = "/{productionId}")
    public ResponseEntity<ProductionResDto> getProduction(@PathVariable long productionId) throws SWException {
        log.info("Received request to get production with id: {}", productionId);
        return new ResponseEntity<>( productionService.getProduction(productionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductionResDto> addProduction(@RequestParam long potStockId, @RequestBody ProductionReqDto productionReqDto) throws SWException {
        log.info("Received request to add production");
        return new ResponseEntity<>( productionService.addProduction(potStockId, productionReqDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{productionId}")
    public ResponseEntity<ProductionResDto> updateProduction(@PathVariable(value = "productionId") long id, @RequestBody ProductionReqDto productionReqDto) throws SWException {
        log.info("Received request to update production details with id: {}", id);
        return new ResponseEntity<>(productionService.updateProduction(id, productionReqDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productionId}")
    public ResponseEntity<ProductionResDto> removeProduction(@PathVariable(value = "productionId") long id) throws SWException {
        log.info("Received request to delete production with id: {}", id);
        return new ResponseEntity<>(productionService.deleteProduction(id), HttpStatus.OK);
    }

}
