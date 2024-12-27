package com.mushroom.analyzer.backend.model.repository;

import com.mushroom.analyzer.backend.model.entity.SaleReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleReturnRepository extends JpaRepository<SaleReturn, Long> {
}
