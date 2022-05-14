package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentTestDao;
import ru.otus.spring.domain.StudentTest;

@Service
public class StudentTestServiceImpl implements StudentTestService {
    private final StudentTestDao dao;

    public StudentTestServiceImpl(StudentTestDao dao) {
        this.dao = dao;
    }

    @Override
    public StudentTest getByName(String testName) {
        return dao.getTest(testName);
    }
}
