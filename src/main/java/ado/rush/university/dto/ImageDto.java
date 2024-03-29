package ado.rush.university.dto;

import ado.rush.university.entity.News;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ImageDto {
    private Long id;
    private String name;
    private byte[] image;

}
