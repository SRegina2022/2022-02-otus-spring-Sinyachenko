package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {
    int count();
    Book getBook(long id);
    List<Book> getAllBooks();
    void insertBook(Book book);
    void updateBook(Book book);
    void deleteBook(long id);

}
