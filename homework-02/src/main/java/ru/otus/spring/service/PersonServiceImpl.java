package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Person;

import java.util.Scanner;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Person getPerson() {
        Scanner scanner = new Scanner(System.in);

        String firstName, lastName;
        System.out.println("First Name: ");
        firstName = scanner.nextLine();

        System.out.println("Last Name: ");
        lastName = scanner.nextLine();

        return new Person(firstName, lastName);
    }
}
