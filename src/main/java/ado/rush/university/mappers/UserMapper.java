package ado.rush.university.mappers;

import ado.rush.university.dto.StudentDetailsDto;
import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.entity.CustomUserDetails;
import ado.rush.university.entity.Student;
import ado.rush.university.entity.UserBaseEntity;

public class UserMapper {

    public static UserBaseEntityDto mapUserToUserDetailsDto(UserBaseEntity user) {
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

    public static UserBaseEntity  mapStudentDetailsDtoToEntity(UserBaseEntityDto user) {
        UserBaseEntity userNew = UserBaseEntity.builder()
                .login(user.getLogin())
                .id(user.getId())
                .password(user.getPassword())
                .dateOfBirth(user.getDateOfBirth())
                .build();


       CustomUserDetails userDetails=  CustomUserDetails.builder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .user(userNew)
               .build();

        userNew.setUserDetails(userDetails);


        return userNew;
    }


}
