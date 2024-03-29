package ado.rush.university.repository;

import ado.rush.university.dto.course.CourseDto;
import ado.rush.university.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<CourseDto> findByName(String courseName );

}
