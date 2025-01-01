package com.mushroom.analyzer.backend.model.dto.res;

import com.mushroom.analyzer.backend.utils.enums.StakeHolderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StakeHolderResDto {
    private long id;
    private StakeHolderType stakeHolderType;
    private String name;
    private String description;
    private String contactNumber;

}
