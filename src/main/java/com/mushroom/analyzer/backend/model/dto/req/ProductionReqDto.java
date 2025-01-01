package com.mushroom.analyzer.backend.model.dto.req;

import com.mushroom.analyzer.backend.utils.enums.MushroomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductionReqDto {
    private LocalDate productionDate;
    private MushroomType mushroomType;
    private double packetWeight;
    private double packetPrice;
    private int numberOfItems;
}
