package ado.rush.university.util;


import ado.rush.university.dto.NewsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class util {
    @Value("#{newsService.getLatestNews().?[getTitle().contains('H') ]}")
    public List<NewsDto> myVal;

    public void cmdrunner(){
            System.out.println(myVal);
    }
}
