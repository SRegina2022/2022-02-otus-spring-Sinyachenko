package ru.otus.spring.service;

import ru.otus.spring.domain.StudentTest;

public interface StudentTestService {
    StudentTest getByName(String testName);
}
