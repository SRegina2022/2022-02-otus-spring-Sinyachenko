package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class StringReadServiceImpl implements StringReadService {
    private final MessageService messageService;

    public StringReadServiceImpl(MessageService msg) {
        this.messageService = msg;
    }

    @Override
    public String read(String messageKey) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(messageService.getMessage(messageKey));
        return scanner.nextLine();
    }
}
