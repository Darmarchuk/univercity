package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("1")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Student extends UserBaseEntity {


    @Builder(builderMethodName = "studentBuilder")
    public Student(Long id,
                   String login,
                   String password,
                   LocalDate dateOfBirth,
                   CustomUserDetails userDetails,
                   List<UserBaseRoles> siteRoles,
                   LocalDateTime modifyDt,
                   List<Enrollment> enrollments,
                   List<StudentRoles> studentRoles
                   ) {
        super(id, login, password, dateOfBirth, userDetails, siteRoles, modifyDt);
        this.enrollments=enrollments;
        this.studentRoles=studentRoles;
    }

    @OneToMany(mappedBy = "student", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();


    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
            , fetch = FetchType.LAZY
            , mappedBy = "user")
    private List<StudentRoles> studentRoles = new ArrayList<>();


}
