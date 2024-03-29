package ado.rush.university.dto;


import ado.rush.university.entity.CustomUserDetails;
import ado.rush.university.entity.Enrollment;
import ado.rush.university.entity.StudentRoles;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class StudentDto {

    private Long id;

    @Length(min = 5,max = 15)
    private String login;

    private LocalDateTime dateOfBirth;

    private CustomUserDetails userDetails;

    private LocalDateTime modifyDt;


    private  List<Enrollment> enrollments = new ArrayList<>();

}
