package ru.otus.spring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    private final int year;
    private final Author author;
    private final Genre genre;
}
