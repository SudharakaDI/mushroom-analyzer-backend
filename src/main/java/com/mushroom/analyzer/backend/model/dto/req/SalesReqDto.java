package com.mushroom.analyzer.backend.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SalesReqDto {
    private int numberOfItems;
    private long stakeHolderId;
}
