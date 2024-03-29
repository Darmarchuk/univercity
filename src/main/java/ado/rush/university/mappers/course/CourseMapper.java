package ado.rush.university.mappers.course;


import ado.rush.university.dto.course.CourseCreateDto;
import ado.rush.university.dto.course.CourseDto;
import ado.rush.university.entity.Course;
import ch.qos.logback.core.model.ComponentModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {
    Course toEntity(CourseDto dto);
    Course toEntity(CourseCreateDto dto);
    CourseDto toDto( Course course);
}
