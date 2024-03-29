package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.StudentTestService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(Main.class);
        StudentTestService service = context.getBean(StudentTestService.class);
        service.run("Simple Math test");
    }
}
