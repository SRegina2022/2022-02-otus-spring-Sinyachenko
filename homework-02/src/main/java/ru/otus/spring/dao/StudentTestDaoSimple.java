package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.StudentTest;
import ru.otus.spring.domain.TestQuestion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

@Component("studentTestDao")
public class StudentTestDaoSimple implements StudentTestDao {
    private final ApplicationConfig applicationConfig;

    public StudentTestDaoSimple(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public StudentTest getTest(String testName, Person person) {
        StudentTest test = new StudentTest(testName);
        ArrayList<TestQuestion> questions = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(applicationConfig.getTestFileName());
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(";");
            questions.add(new TestQuestion(line[0], line[1]));
        }
        test.setQuestions(questions);
        test.setStudent(person);
        return test;
    }

    @Override
    public int getAnswersToPass() {
        return applicationConfig.getAnswersToPass();
    }
}
