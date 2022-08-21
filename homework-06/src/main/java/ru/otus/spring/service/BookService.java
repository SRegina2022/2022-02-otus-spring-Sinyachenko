package ru.otus.spring.service;

import ru.otus.spring.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    int count();
    Optional<Book> getBook(long id);
    List<Book> getAllBooks();
    void insertBook(Book book);
    void updateBook(Book book);
    void deleteBook(long id);

}
