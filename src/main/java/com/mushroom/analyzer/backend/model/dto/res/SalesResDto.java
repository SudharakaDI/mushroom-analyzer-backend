package com.mushroom.analyzer.backend.model.dto.res;

import com.mushroom.analyzer.backend.model.entity.Expense;
import com.mushroom.analyzer.backend.model.entity.Income;
import com.mushroom.analyzer.backend.model.entity.StakeHolder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SalesResDto {
    private long id;
    private int numberOfItems;
    private Income income;
    private Expense expense;
    private StakeHolder stakeHolder;
}
