package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.StudentTestDao;
import ru.otus.spring.dao.StudentTestDaoSimple;

@Configuration
public class DaoConfig {
    @Autowired
    private ApplicationConfig applicationConfig1;

    @Bean
    public StudentTestDao studentTestDao(ApplicationConfig applicationConfig) {

        return new StudentTestDaoSimple(applicationConfig1);
    }
}
