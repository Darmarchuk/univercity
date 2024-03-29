package ado.rush.university.mappers;


import ado.rush.university.entity.Image;
import ado.rush.university.dto.ImageDto;

public class ImageMapper {
    public static ImageDto entityToDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .image(image.getImage())
                .name(image.getName())
                .build();

    }
}
