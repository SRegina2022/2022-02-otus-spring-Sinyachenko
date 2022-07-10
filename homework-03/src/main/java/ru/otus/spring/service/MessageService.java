package ru.otus.spring.service;

public interface MessageService {
    String getMessage(String key);

    String getMessage(String key, Object[] objectsFormat);
}
