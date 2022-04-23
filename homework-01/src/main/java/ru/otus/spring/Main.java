package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.StudentTest;
import ru.otus.spring.domain.TestQuestion;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.StudentTestService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        StudentTestService service = context.getBean(StudentTestService.class);
        StudentTest test = service.getByName("Simple Math test");
        Scanner scanner = new Scanner(System.in);

        System.out.println(test.getName());
        for (TestQuestion question: test.getQuestions()) {
            question.Ask();
            String answer = scanner.nextLine();
            if (question.Check(answer)) {
                System.out.println("OK");
            } else {
                System.out.println("Failed");
            }
        }

        // Данная операция, в принципе не нужна.
        // Мы не работаем пока что с БД, а Spring Boot сделает закрытие за нас
        // Подробности - через пару занятий
        context.close();
    }
}
