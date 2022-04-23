package ru.otus.spring.service;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.StudentTestDao;
import ru.otus.spring.domain.StudentTest;

public class StudentTestServiceImpl implements StudentTestService {
    private final StudentTestDao dao;

    public StudentTestServiceImpl(StudentTestDao dao) {
        this.dao = dao;
    }

    public StudentTest getByName(String testName) {
        return dao.getTest(testName);
    }
}
