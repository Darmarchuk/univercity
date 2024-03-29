package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("2")
public class Trainer extends UserBaseEntity {

    @ManyToOne
    @JoinColumn(name = "department_id"
            , nullable = true
            , foreignKey = @ForeignKey(name = "fk_trainer_department"))
    Department department;


    @Builder(builderMethodName = "trainerBuilder")
    public Trainer(Long id, String login, String password, LocalDate dateOfBirth, CustomUserDetails userDetails, List<UserBaseRoles> siteRoles, LocalDateTime modifyDt) {
        super(id, login, password, dateOfBirth, userDetails, siteRoles, modifyDt);
    }
}
