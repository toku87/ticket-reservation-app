package com.github.java4wro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
