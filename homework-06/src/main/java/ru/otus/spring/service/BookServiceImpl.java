package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Book;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository dao;

    @Autowired
    public BookServiceImpl(BookRepository dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.findAll().size();
    }

    @Override
    public Optional<Book> getBook(long id) {
        return dao.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return dao.findAll();
    }

    @Override
    public void insertBook(Book book) {
        dao.save(book);
    }

    @Override
    public void updateBook(Book book) {
        dao.save(book);
    }

    @Override
    public void deleteBook(long id) {
        dao.deleteById(id);
    }
}
