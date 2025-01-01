package com.mushroom.analyzer.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mushroom.analyzer.backend.utils.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = 0")
@SQLDelete(sql = "UPDATE expense SET is_deleted=1 WHERE id = ?")
public class Expense extends AbstractEntity{

    private String description;
    private double amount;
    private ExpenseType type;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
