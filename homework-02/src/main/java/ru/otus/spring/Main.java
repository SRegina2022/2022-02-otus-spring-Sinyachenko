package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.domain.StudentTest;
import ru.otus.spring.domain.TestQuestion;
import ru.otus.spring.service.StudentTestService;

import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(Main.class);
        StudentTestService service = context.getBean(StudentTestService.class);
        StudentTest test = service.getByName("Simple Math test");
        Scanner scanner = new Scanner(System.in);

        String firstName, lastName;
        System.out.println("First Name: ");
        firstName = scanner.nextLine();

        System.out.println("Last Name: ");
        lastName = scanner.nextLine();

        System.out.println(test.getName());

        int correctAnswers = 0;
        for (TestQuestion question: test.getQuestions()) {
            question.Ask();
            String answer = scanner.nextLine();
            if (question.Check(answer)) {
                correctAnswers++;
            }
        }
        System.out.println("Test result for " + firstName + " " +lastName + ":");
        System.out.println("Correct answers:" + correctAnswers);
        if (correctAnswers > context.getBean(ApplicationConfig.class).getAnswersToPass()) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }
}
