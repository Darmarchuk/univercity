package ado.rush.university.mappers;

import ado.rush.university.dto.CreateNewsDto;
import ado.rush.university.dto.ImageDto;
import ado.rush.university.dto.ImageWrapper;
import ado.rush.university.dto.NewsDto;
import ado.rush.university.entity.Image;
import ado.rush.university.entity.News;

import java.util.List;
import java.util.stream.Collectors;

public class NewsMapper {

    public static News dtoToEntity(CreateNewsDto dto){
        News news =  News.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .description(dto.getDescription())
                .images(dto.getImageList())
                .build();
        List<Image> images = news.getImages();
        images.forEach(x-> x.setNews(news));
        return  news;
    }

    public static NewsDto entityToDto(News news){
        ImageWrapper iw = new ImageWrapper();

         news.getImages().stream()
                .map(ImageMapper::entityToDto)
                .map(i-> {
                    iw.addImage(i);
                    return iw;
                })
                .collect(Collectors.toList());

        NewsDto newsDto =  NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .description(news.getDescription())
                .imageList(iw )
                .build();
        return  newsDto;
    }
}
