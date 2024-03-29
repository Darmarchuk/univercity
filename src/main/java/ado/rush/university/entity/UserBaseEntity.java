package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Entity(name = "AppUser")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "AppUser", uniqueConstraints = {
        @UniqueConstraint(name = "user_login", columnNames = {"login"})
})
@Builder
@NoArgsConstructor
@AllArgsConstructor

@NamedEntityGraph(name = "UserBaseEntity.siteRoles",
        attributeNodes = @NamedAttributeNode("siteRoles"))

@SqlResultSetMapping(
        name="UserTypeResult",
        classes={
                @ConstructorResult(
                        targetClass=UserBaseTypeDto.class,
                        columns={
                                @ColumnResult(name="id", type=Long.class),
                                @ColumnResult(name="user_type", type=Integer.class)
                        })})

@NamedNativeQuery(name = "UserBaseEntity.findUserTypeByLogin",
        query = "SELECT id, user_type FROM app_user WHERE login = ?1 ",
        resultSetMapping = "UserTypeResult")

public class UserBaseEntity {
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @NaturalId
    @Column(name = "login", length = 50, nullable = false, unique = true)
    private String login;

    private String password;

    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private CustomUserDetails userDetails;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
            , fetch = FetchType.LAZY
            , mappedBy = "user")
    private List<UserBaseRoles> siteRoles = new ArrayList<>();

    @LastModifiedDate
    private LocalDateTime modifyDt;

    @ElementCollection
    @CollectionTable(name = "image",joinColumns = @JoinColumn(name= "user_id"))
    @OrderBy("name desc")
    @Column(name = "name")
    Set<String> userImages = new HashSet<>();

    public UserBaseEntity(Long id, String login, String password, LocalDate dateOfBirth, CustomUserDetails userDetails, List<UserBaseRoles> siteRoles, LocalDateTime modifyDt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userDetails = userDetails;
        this.siteRoles = siteRoles;
        this.modifyDt = modifyDt;
    }
}
