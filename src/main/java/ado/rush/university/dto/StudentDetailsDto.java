package ado.rush.university.dto;

import ado.rush.university.dto.validgroups.StudentInfo;
import ado.rush.university.dto.validgroups.BasicInfo;
import ado.rush.university.entity.UserType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;


@ToString
public class StudentDetailsDto extends UserBaseEntityDto {


    @Builder(builderMethodName = "dtoBuilder")
    public StudentDetailsDto(Long id, @Length(message = "incorrect first name", min = 3, max = 100, groups = BasicInfo.class)
    String firstName, @Length(message = "incorrect last name", min = 3, max = 100, groups = BasicInfo.class)
    String lastName, @Email(message = "incorrect email", groups = BasicInfo.class) String email,
                             @Length(message = "incorrect login", min = 3, max = 100, groups = BasicInfo.class) String login,
                             @Past(message = "Date of birth must be in past", groups = BasicInfo.class) LocalDate dateOfBirth,
                             @Valid @Size(message = "пользователю сайта должна быть назначена роль", min = 1, groups = BasicInfo.class)
                             List<String> roles, String password, @NotNull(message = "Тип пользователя не должен быть пустым", groups = BasicInfo.class) UserType userType,
                             @Valid @NotNull(message = "студенту должна быть назначена роль", groups = StudentInfo.class)
                             @Size(message = "студенту должна быть назначена роль", min = 1, groups = StudentInfo.class) List<String> studentRoles) {
        super(id, firstName, lastName, email, login, dateOfBirth, roles, password, userType, studentRoles);
    }
}
