package ado.rush.university.mappers;

import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.entity.Dean;
import ado.rush.university.entity.Trainer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DeanMapper{

    Dean mapToEntity(UserBaseEntityDto dto);
    UserBaseEntityDto toDto(Dean entity);

}
