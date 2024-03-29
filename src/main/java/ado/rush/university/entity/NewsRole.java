package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name= "news_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRole {
    @Id
    @SequenceGenerator(name = "news_role_id_seq",
            sequenceName = "news_role_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_role_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private UserBaseEntity user;

    private SiteRole siteRole;

}
