package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.StudentTest;

public interface StudentTestDao {
    StudentTest getTest(String testName, Person person);
    int getAnswersToPass();
}
