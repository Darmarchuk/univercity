package ado.rush.university;

import ado.rush.university.util.util;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UnivercityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UnivercityApplication.class, args);
         util utilBean = context.getBean(util.class);
         utilBean.cmdrunner();


    }


@Bean
public Faker getFaker(){
        return  new Faker();
}


}

