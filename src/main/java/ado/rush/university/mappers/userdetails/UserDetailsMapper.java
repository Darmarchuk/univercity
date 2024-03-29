package ado.rush.university.mappers.userdetails;

import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.entity.CustomUserDetails;
import ado.rush.university.entity.UserBaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import javax.swing.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,typeConversionPolicy = ReportingPolicy.IGNORE)
public interface UserDetailsMapper {

    @Mapping(source = "id",target = "id", ignore = true)
    CustomUserDetails toEntity ( UserBaseEntityDto dto );

}
