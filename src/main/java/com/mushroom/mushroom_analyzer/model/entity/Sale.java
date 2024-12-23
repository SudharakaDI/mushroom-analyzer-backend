package com.mushroom.mushroom_analyzer.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
@SQLDelete(sql = "UPDATE sale SET is_deleted=1 WHERE id = ?")
public class Sale extends AbstractEntity{

    @Column(nullable = false)
    private int numberOfItems;

    @OneToOne(cascade = CascadeType.ALL)
    private SaleReturn saleReturn;

    @OneToOne(cascade = CascadeType.ALL)
    private Expense expense;

    @OneToOne(cascade = CascadeType.ALL)
    private Income income;

}