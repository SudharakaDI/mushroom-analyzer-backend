package com.mushroom.mushroom_analyzer.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mushroom.mushroom_analyzer.utils.enums.MushroomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = 0")
@SQLDelete(sql = "UPDATE production SET is_deleted=1 WHERE id = ?")
public class Production extends AbstractEntity{

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionDate;

    @Column(nullable = false)
    private MushroomType mushroomType;

    @Column(nullable = false)
    private double packetWeight;

    @Column(nullable = false)
    private double packetPrice;

    @Column(nullable = false)
    private int numberOfItems;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "production_id")
    private List<Sale> sales;
}
