package ado.rush.university.service;

import ado.rush.university.dto.course.CourseCreateDto;
import ado.rush.university.dto.course.CourseDto;

public interface ICourseService {
    CourseDto create(CourseCreateDto dto);
}
