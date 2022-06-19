package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Person;

import java.util.Scanner;

@Service
public class PersonServiceImpl implements PersonService {
    private final MessageService messageService;

    public PersonServiceImpl(MessageService msg) {
        this.messageService = msg;
    }

    @Override
    public Person getPerson() {
        Scanner scanner = new Scanner(System.in);

        String firstName, lastName;
        System.out.println(messageService.getMessage("firstName"));
        firstName = scanner.nextLine();

        System.out.println(messageService.getMessage("lastName"));
        lastName = scanner.nextLine();

        return new Person(firstName, lastName);
    }
}
