package ado.rush.university.dto.course;


import ado.rush.university.entity.Trainer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateDto {

    private Long Id;

    @Length(min=1 ,max=100,message = "name string length withing 1 to 200 symbols ")
    private String name;
    @Length(min=1 ,max=1500,message = "description string length withing 1 to 1500 symbols ")
    private String description;

    @NotNull(message = "trainer not defined")
    private Trainer trainer;

}
