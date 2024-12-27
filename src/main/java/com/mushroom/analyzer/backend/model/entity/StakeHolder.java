package com.mushroom.analyzer.backend.model.entity;

import com.mushroom.analyzer.backend.utils.enums.StakeHolderType;
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
@SQLDelete(sql = "UPDATE stake_holder SET is_deleted=1 WHERE id = ?")
public class StakeHolder extends AbstractEntity{
    private StakeHolderType stakeHolderType;
    private String name;
    private String description;
    private String contactNumber;

}
