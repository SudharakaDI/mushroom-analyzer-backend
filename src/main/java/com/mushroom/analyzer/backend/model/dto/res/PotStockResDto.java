package com.mushroom.analyzer.backend.model.dto.res;

import com.mushroom.analyzer.backend.utils.enums.MushroomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PotStockResDto {

    private long id;
    private MushroomType mushroomType;
    private StakeHolderResDto mushroomSupplier;
    private int numberOfPots;
    private String description;
    private List<ExpenseResDto> expenses;
    private List<ProductionResDto> productions;
}
