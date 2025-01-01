package com.mushroom.analyzer.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = 0")
@SQLDelete(sql = "UPDATE sale_return SET is_deleted=1 WHERE id = ?")
public class SaleReturn extends AbstractEntity{
    private String description;
    @Column(nullable = false)
    private int numberOfItems;
}
