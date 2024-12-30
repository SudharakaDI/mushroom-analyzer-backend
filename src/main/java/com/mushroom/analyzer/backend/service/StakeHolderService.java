package com.mushroom.analyzer.backend.service;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.model.entity.StakeHolder;

public interface StakeHolderService {
    StakeHolder getStakeHolder(long id) throws SWException;
    StakeHolder getStakeHolderById(long id) throws SWException;
}
