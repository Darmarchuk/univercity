package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name= "student_roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRoles {
    @Id
    @SequenceGenerator(name = "student_role_id_seq",
            sequenceName = "student_role_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_role_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name ="user_id",nullable = false)
    private Student user;

    private SiteRole siteRole;




    @Override
    public String toString() {
        return "StudentRoles{" +
                "user=" + user +
                ", siteRole=" + siteRole.getName() +
                '}';
    }
}

