package com.mushroom.analyzer.backend.model.entity;

import com.mushroom.analyzer.backend.utils.enums.MushroomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = 0")
@SQLDelete(sql = "UPDATE pot_stock SET is_deleted=1 WHERE id = ?")
public class PotStock extends AbstractEntity{
    private MushroomType mushroomType;
    private int numberOfPots;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private StakeHolder mushroomSupplier;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "pot_stock_id")
    private List<Expense> expenses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "pot_stock_id")
    private List<Production> productions;

}
