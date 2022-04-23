package ru.otus.spring.dao;

import ru.otus.spring.domain.StudentTest;
import ru.otus.spring.domain.TestQuestion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentTestDaoSimple implements StudentTestDao {
    private String csvFileName = "";
    @Override
    public StudentTest getTest(String testName) {
        StudentTest test = new StudentTest(testName);
        ArrayList<TestQuestion> questions = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFileName);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(";");
            questions.add(new TestQuestion(line[0], line[1]));
        }
        test.setQuestions(questions);
        return test;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }
}
