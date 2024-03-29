package ado.rush.university.dto;


import ado.rush.university.entity.Image;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class NewsDto {
    @NotNull
    private Long id;
    @Length(min = 1,max = 200)
    private String title;
    @Length(min = 1,max = 500)
    private String description;
    @Length(min = 1,max = 50000)
    private String content;
    @FutureOrPresent
    private LocalDateTime dateFrom;

    private ImageWrapper imageList;

}
