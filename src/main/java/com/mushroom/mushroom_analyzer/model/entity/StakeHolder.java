package com.mushroom.mushroom_analyzer.model.entity;

import com.mushroom.mushroom_analyzer.utils.enums.StakeHolderType;
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
