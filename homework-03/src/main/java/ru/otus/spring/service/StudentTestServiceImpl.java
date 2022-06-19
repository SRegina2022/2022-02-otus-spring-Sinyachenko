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
    private final MessageService messageService;

    public StudentTestServiceImpl(StudentTestDao dao, MessageService msg)
    {
        this.dao = dao;
        this.messageService = msg;
    }

    @Override
    public void Run(String testName) {
        PersonService personService = new PersonServiceImpl(messageService);
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
        Object[] objectsFormat = {test.getStudent()};
        System.out.println(messageService.getMessage("testResultHeader", objectsFormat));
        System.out.println(messageService.getMessage("correctAnswers") + correctAnswers);
        if (correctAnswers > dao.getAnswersToPass()) {
            System.out.println(messageService.getMessage("testPassed"));
        } else {
            System.out.println(messageService.getMessage("testFailed"));
        }
    }


}
