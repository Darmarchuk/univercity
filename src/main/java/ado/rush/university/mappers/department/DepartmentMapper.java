package ado.rush.university.mappers.department;


import ado.rush.university.dto.department.DepartmentDto;
import ado.rush.university.entity.Department;
import org.mapstruct.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DepartmentMapper {

    DepartmentDto toDto(Department entity);
    Department toEntity(DepartmentDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDepartmentFromDto(DepartmentDto dto, @MappingTarget Department department );
}
