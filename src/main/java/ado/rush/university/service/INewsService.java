package ado.rush.university.service;

import ado.rush.university.dto.CreateNewsDto;
import ado.rush.university.dto.ImageProjection;
import ado.rush.university.dto.NewsDto;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface INewsService {
     List<NewsDto> getLatestNews();
     Page<NewsDto> getLatestNews(Pageable pageable);

     NewsDto create(CreateNewsDto news);

     void save(CreateNewsDto newsDto);

     ImageProjection getNewsImages(Long newsId, Long imageId);
}
