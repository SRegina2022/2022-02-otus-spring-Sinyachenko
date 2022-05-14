package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.StudentTestDao;
import ru.otus.spring.service.StudentTestService;
import ru.otus.spring.service.StudentTestServiceImpl;

@Configuration
public class ServicesConfig {

    @Bean
    public StudentTestService studentTestService(StudentTestDao dao) {
        return new StudentTestServiceImpl(dao);
    }
}
