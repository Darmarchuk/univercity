package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomUserDetails  {
    @Id
    @SequenceGenerator(name = "userdetails_id_seq",sequenceName = "userdetails_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userdetails_id_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @LastModifiedDate
    private LocalDateTime modifyDt;
    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="user_user_detail_fk")
    )
    private UserBaseEntity user;

}
