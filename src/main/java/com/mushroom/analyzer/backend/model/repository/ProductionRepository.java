package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductionRepository extends JpaRepository<Production, Long> {
    List<Production> findAllByOrderByProductionDateDesc();
    List<Production> findProductionByProductionDate(LocalDate productionDate);

}
