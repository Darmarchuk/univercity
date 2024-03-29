package ado.rush.university.dto.course;


import ado.rush.university.entity.Enrollment;
import ado.rush.university.entity.Trainer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDto {
    private Long Id;
    private String name;
    private String description;
    private Trainer trainer;
    private List<Enrollment> enrollment;

}
