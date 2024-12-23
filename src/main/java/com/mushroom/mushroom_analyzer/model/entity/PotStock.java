package com.mushroom.mushroom_analyzer.model.entity;

import com.mushroom.mushroom_analyzer.utils.enums.MushroomType;
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

    @OneToOne(fetch = FetchType.LAZY)
    private StakeHolder mushroomSupplier;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pot_stock_id")
    private List<Expense> expenses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pot_stock_id")
    private List<Production> productions;

}
