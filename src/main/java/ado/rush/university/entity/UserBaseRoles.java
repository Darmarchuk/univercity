package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "userbase_roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseRoles {
    @Id
    @SequenceGenerator(name = "userbase_role_id_seq",
            sequenceName = "userbase_role_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userbase_role_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id",nullable = false)
    private UserBaseEntity user;

    private SiteRole siteRole;

    @Override
    public String toString() {
        return "UserRoles{" +
                "user=" + user +
                ", siteRole=" + siteRole.getName() +
                '}';
    }
}

