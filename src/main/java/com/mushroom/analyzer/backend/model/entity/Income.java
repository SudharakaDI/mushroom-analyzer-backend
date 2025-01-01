package com.mushroom.analyzer.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@SQLDelete(sql = "UPDATE income SET is_deleted=1 WHERE id = ?")
public class Income extends AbstractEntity{

    private String description;
    private double amount;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
