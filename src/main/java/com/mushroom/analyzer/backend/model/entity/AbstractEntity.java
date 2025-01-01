package com.mushroom.analyzer.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", columnDefinition = "DATETIME(0)")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified_date", columnDefinition = "DATETIME(0)")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime lastModifiedDateTime;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean isDeleted;
}