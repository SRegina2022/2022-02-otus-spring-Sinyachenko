package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Person;

import java.util.Scanner;

@Service
public class PersonServiceImpl implements PersonService {
    private final StringReadService stringReadService;

    public PersonServiceImpl(StringReadService msg) {
        this.stringReadService = msg;
    }

    @Override
    public Person getPerson() {
        String firstName = stringReadService.read("firstName");
        String lastName = stringReadService.read("lastName");

        return new Person(firstName, lastName);
    }
}
