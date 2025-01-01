package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
