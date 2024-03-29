package ado.rush.university.dto.department;


import ado.rush.university.entity.Trainer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {

    private Long id;
    @Size(min = 1,max = 200)
    private String name;
    private List<Trainer> trainerList = new ArrayList<>();
}
