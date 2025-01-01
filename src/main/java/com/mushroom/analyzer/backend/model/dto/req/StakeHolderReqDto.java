package com.mushroom.analyzer.backend.model.dto.req;

import com.mushroom.analyzer.backend.utils.enums.MushroomType;
import com.mushroom.analyzer.backend.utils.enums.StakeHolderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StakeHolderReqDto {
    private StakeHolderType stakeHolderType;
    private MushroomType mushroomType;
    private int numberOfPots;
    private String description;
}
