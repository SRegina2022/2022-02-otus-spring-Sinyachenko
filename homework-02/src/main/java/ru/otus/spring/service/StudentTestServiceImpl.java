package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentTestDao;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.StudentTest;
import ru.otus.spring.domain.TestQuestion;

import java.util.Scanner;

@Service
public class StudentTestServiceImpl implements StudentTestService {
    private final StudentTestDao dao;

    public StudentTestServiceImpl(StudentTestDao dao) {
        this.dao = dao;
    }

    @Override
    public void Run(String testName) {
        PersonService personService = new PersonServiceImpl();
        Person person = personService.getPerson();
        StudentTest test = dao.getTest(testName, person);

        System.out.println(test.getName());
        Scanner scanner = new Scanner(System.in);

        int correctAnswers = 0;
        for (TestQuestion question: test.getQuestions()) {
            question.Ask();
            String answer = scanner.nextLine();
            if (question.Check(answer)) {
                correctAnswers++;
            }
        }
        System.out.println("Test result for " + test.getStudent() + ":");
        System.out.println("Correct answers:" + correctAnswers);
        if (correctAnswers > dao.getAnswersToPass()) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }


}
