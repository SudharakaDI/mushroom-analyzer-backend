package com.mushroom.analyzer.backend.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IncomeReqDto {
    private String description;
    private double amount;
    private LocalDate date;
}
