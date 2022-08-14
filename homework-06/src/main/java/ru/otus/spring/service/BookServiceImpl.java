package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao dao;

    @Autowired
    public BookServiceImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public Book getBook(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return dao.getAll();
    }

    @Override
    public void insertBook(Book book) {
        dao.insert(book);
    }

    @Override
    public void updateBook(Book book) {
        dao.update(book);
    }

    @Override
    public void deleteBook(long id) {
        dao.deleteById(id);
    }
}
