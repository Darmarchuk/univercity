package ado.rush.university.mappers;

import ado.rush.university.dto.StudentDetailsDto;
import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.entity.CustomUserDetails;
import ado.rush.university.entity.Student;
import ado.rush.university.entity.UserBaseEntity;

public class StudentMapper {

    public static UserBaseEntityDto mapStudentToUserDetailsDto(Student user) {
        return UserBaseEntityDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getUserDetails().getEmail())
                .firstName(user.getUserDetails().getFirstName())
                .lastName(user.getUserDetails().getLastName())
                .password(user.getPassword())
                .build();
    }

    public static StudentDetailsDto mapUserToUserDetailsDto(UserBaseEntity user) {
        return StudentDetailsDto.dtoBuilder()
                .id(user.getId())
                .login(user.getLogin())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getUserDetails().getEmail())
                .firstName(user.getUserDetails().getFirstName())
                .lastName(user.getUserDetails().getLastName())
                .password(user.getPassword())
                .build();
    }

    public static Student mapStudentDetailsDtoToEntity(UserBaseEntityDto user) {
        Student studentNew =  Student.studentBuilder()
                .login(user.getLogin())
                .password(user.getPassword())
                .dateOfBirth(user.getDateOfBirth())
                .build();
       CustomUserDetails userDetails=  CustomUserDetails.builder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .user(studentNew)
               .build();
        studentNew.setUserDetails(userDetails);
        return studentNew;
    }


}
