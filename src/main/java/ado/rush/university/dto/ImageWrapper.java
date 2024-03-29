package ado.rush.university.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ImageWrapper {
    private List<ImageDto> imageList =new ArrayList<>();

    public void addImage(ImageDto imageDto){
        imageList.add(imageDto);
    }

}
