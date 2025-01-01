package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.StakeHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakeHolderRepository extends JpaRepository<StakeHolder, Long> {
}
