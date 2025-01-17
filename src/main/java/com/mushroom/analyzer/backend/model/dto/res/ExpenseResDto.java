package com.mushroom.analyzer.backend.model.dto.res;

import com.mushroom.analyzer.backend.utils.enums.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExpenseResDto {
    private long id;
    private String description;
    private double amount;
    private ExpenseType type;
    private LocalDate date;
}
