package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.StakeHolder;
import com.mushroom.analyzer.backend.utils.enums.StakeHolderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StakeHolderRepository extends JpaRepository<StakeHolder, Long> {
    List<StakeHolder> findAllByStakeHolderType(StakeHolderType stakeHolderType);
}
