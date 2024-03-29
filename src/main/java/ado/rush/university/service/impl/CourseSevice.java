package ado.rush.university.service.impl;

import ado.rush.university.dto.course.CourseCreateDto;
import ado.rush.university.dto.course.CourseDto;
import ado.rush.university.entity.Course;
import ado.rush.university.mappers.course.CourseMapper;
import ado.rush.university.repository.CourseRepository;
import ado.rush.university.service.ICourseService;
import org.springframework.stereotype.Service;


@Service
public class CourseSevice implements ICourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper mapper;

    public CourseSevice(CourseRepository courseRepository, CourseMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public CourseDto create(CourseCreateDto dto) {
        Course course = courseRepository.save(mapper.toEntity(dto));
        return mapper.toDto(course);
    }
}
