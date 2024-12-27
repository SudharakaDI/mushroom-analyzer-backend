package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Production, Long> {
}
