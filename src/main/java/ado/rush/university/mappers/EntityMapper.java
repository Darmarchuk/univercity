package ado.rush.university.mappers;

import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.entity.CustomUserDetails;
import ado.rush.university.entity.UserBaseEntity;

public class  EntityMapper<T extends UserBaseEntityDto ,P extends  UserBaseEntity> {

    private final T baseDtoBldr;
    private final P baseEntityBldr;

    public EntityMapper(T baseDtoBldr, P baseEntityBldr) {
        this.baseDtoBldr = baseDtoBldr;
        this.baseEntityBldr = baseEntityBldr;
    }

    public T mapUserToUserDetailsDto(P user) {
        return (T)baseDtoBldr.builder()
                .id(user.getId())
                .login(user.getLogin())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getUserDetails().getEmail())
                .firstName(user.getUserDetails().getFirstName())
                .lastName(user.getUserDetails().getLastName())
                .password(user.getPassword())
                .build();
    }

    public  P  mapDtoToEntity(UserBaseEntityDto user) {
        P userNew = (P) baseEntityBldr.builder()
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
