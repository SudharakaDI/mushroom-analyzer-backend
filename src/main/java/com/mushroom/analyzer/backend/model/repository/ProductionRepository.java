package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionRepository extends JpaRepository<Production, Long> {
    List<Production> findAllByOrderByProductionDateDesc();


}
