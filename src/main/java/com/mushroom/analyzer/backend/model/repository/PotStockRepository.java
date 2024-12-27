package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.PotStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotStockRepository extends JpaRepository<PotStock, Long> {
}
