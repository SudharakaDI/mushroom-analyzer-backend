package com.mushroom.analyzer.backend.model.dto.res;

import com.mushroom.analyzer.backend.model.entity.Sale;
import com.mushroom.analyzer.backend.utils.enums.MushroomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductionResDto {
    private long id;
    private LocalDate productionDate;
    private MushroomType mushroomType;
    private double packetWeight;
    private double packetPrice;
    private int numberOfItems;
    private List<Sale> sales;
}
