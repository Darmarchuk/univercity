package ado.rush.university.service.impl;

import ado.rush.university.dto.CreateNewsDto;
import ado.rush.university.dto.ImageProjection;
import ado.rush.university.dto.NewsDto;
import ado.rush.university.mappers.NewsMapper;
import ado.rush.university.repository.ImageRepository;
import ado.rush.university.repository.NewsRepository;
import ado.rush.university.service.INewsService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NewsService implements INewsService {

    private final Faker faker;

    private final List<NewsDto> news;

    private final NewsRepository newsRepository;
    private final ImageRepository imageRepository;
    @Autowired
    public NewsService(Faker faker, NewsRepository newsRepository, ImageRepository imageRepository) {
        this.faker = faker;
        this.newsRepository = newsRepository;
        this.imageRepository = imageRepository;
        this.news =getLatestNews();
    }

    @Override
    public List<NewsDto> getLatestNews() {
        Random rnd = new Random();
        List<NewsDto> myNews = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            NewsDto newsDto = NewsDto.builder()
                    .id(rnd.nextLong())
                    .description(faker.ancient().god())
                    .title(faker.ancient().primordial())
                    .dateFrom(LocalDateTime.ofInstant( (faker.date().between(Date.valueOf("2000-01-01"), Date.valueOf("2025-01-01"))).toInstant(), ZoneId.of("Europe/Paris")))
                    .build();
            myNews.add(newsDto);


        }
        return myNews;
    }

    @Override
    public Page<NewsDto> getLatestNews(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startfrom= pageSize * currentPage;
        List<NewsDto> newsDtos = Collections.emptyList();

        if(startfrom >= 0 ){
            newsDtos = newsRepository.findAll(pageable).stream().map(NewsMapper::entityToDto).collect(Collectors.toList());
        return  new PageImpl<>( newsDtos,PageRequest.of(currentPage,pageSize), newsRepository.count() );
        }


//             newsDtos = news.subList(startfrom, Math.min(startfrom+pageSize   , news.size()  ));



        return new PageImpl<>(newsDtos , PageRequest.of(currentPage,pageSize ),news.size() );
    }

    @Override
    public NewsDto create(CreateNewsDto NewsDto ) {


        return null;
    }

    @Override
    public void save(CreateNewsDto newsDto) {

        newsRepository.save(NewsMapper.dtoToEntity(newsDto));

    }

    @Override
    public ImageProjection getNewsImages(Long newsId, Long imageId) {
        return imageRepository.findByNewsIdAndId(newsId,imageId);

    }
}
