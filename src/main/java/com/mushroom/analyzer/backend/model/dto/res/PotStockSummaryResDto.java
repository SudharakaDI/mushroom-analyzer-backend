package com.mushroom.analyzer.backend.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PotStockSummaryResDto {
    private int totalProduction;
    private double totalIncome;
    private double totalExpense;
    private double totalProfit;
}
