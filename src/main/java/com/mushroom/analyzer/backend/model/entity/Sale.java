package com.mushroom.analyzer.backend.model.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(nullable = false)
    private StakeHolder seller;

}
