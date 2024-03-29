package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@DiscriminatorValue("3")
public class Dean extends UserBaseEntity {

    @Builder(builderMethodName = "deanBuilder")
    public Dean(Long id, String login, String password, LocalDate dateOfBirth, CustomUserDetails userDetails, List<UserBaseRoles> siteRoles, LocalDateTime modifyDt) {
        super(id, login, password, dateOfBirth, userDetails, siteRoles, modifyDt);
    }
}
