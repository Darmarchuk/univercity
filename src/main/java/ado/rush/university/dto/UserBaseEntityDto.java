package ado.rush.university.dto;

import ado.rush.university.dto.validgroups.StudentInfo;
import ado.rush.university.dto.validgroups.BasicInfo;
import ado.rush.university.dto.validgroups.TrainerInfo;
import ado.rush.university.entity.Department;
import ado.rush.university.entity.UserType;
import ado.rush.university.util.PasswordValid;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBaseEntityDto implements UserDetails {

    private Long id;

    // student constructor
    public UserBaseEntityDto(Long id, String firstName, String lastName, String email, String login, LocalDate dateOfBirth, List<String> roles, String password,List<String> studentRoles, UserType userType ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
        this.password = password;
        this.userType = userType;
        this.studentRoles = studentRoles;
    }

    //trainer constructor
    public UserBaseEntityDto(Long id, String firstName, String lastName, String email, String login, LocalDate dateOfBirth, List<String> roles, String password, UserType userType, List<String> departments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
        this.password = password;
        this.userType = userType;
        this.departments = departments;
    }

    @Length(message = "incorrect first name",min = 3, max = 100,groups = BasicInfo.class)
    private String firstName;
    @Length(message = "incorrect last name",min = 3, max = 100,groups = BasicInfo.class)
    private String lastName;

    @Email(message = "incorrect email",groups = BasicInfo.class)
    private String email;

    @Length(message = "incorrect login",min = 3, max = 100,groups = BasicInfo.class)
    private String login;


    @Past(message = "Date of birth must be in past",groups = BasicInfo.class)
    private LocalDate dateOfBirth;

    @Valid
    @Size(message = "пользователю сайта должна быть назначена роль",min = 1, groups = BasicInfo.class)
    private List<String> roles;


    @PasswordValid(groups = BasicInfo.class)
    private String password;

    @NotNull(message = "Тип пользователя не должен быть пустым",groups = BasicInfo.class)
    private UserType userType;

    @Valid
    @NotNull(message = "студенту должна быть назначена роль", groups = StudentInfo.class)
    @Size(message = "студенту должна быть назначена роль",min = 1, groups = StudentInfo.class)
    private List<String> studentRoles;

    @NotNull(message = "Департамент тренера не должне быть пустым",groups = TrainerInfo.class)
    private List<String> departments = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
