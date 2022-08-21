package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.BookService;

import javax.validation.constraints.Null;
import java.util.List;


@ShellComponent
public class ShellLibraryDb {
    private final BookService bookService;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Autowired
    ShellLibraryDb(BookService bookService, AuthorDao authorDao, GenreDao genreDao) {
        this.bookService = bookService;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @ShellMethod("Get count of books")
    public int countBook() {
        return bookService.count();
    }

    @ShellMethod("Get Book by ID")
    public Book getBook(@ShellOption Integer id) {
        return bookService.getBook(id);
    }

    @ShellMethod("Get all Books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }


    @ShellMethod("Create Book by (ID, Name, Year, AuthorId, GenreId)")
    public void insertBook(@ShellOption Integer id,
                        @ShellOption String name,
                        @ShellOption Integer year,
                        @ShellOption Integer authorId,
                        @ShellOption Integer genreId) {
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);
        Book book = new Book(id, name, year, author, genre);
        bookService.insertBook(book);
    }

    @ShellMethod("Delete Book by ID")
    public void deleteBook(@ShellOption Integer id) {
        bookService.deleteBook(id);
    }

    @ShellMethod("Update Book by (ID, Name, Year, AuthorId, GenreId)")
    public void updateBook(@ShellOption Integer id,
                           @ShellOption String name,
                           @ShellOption Integer year,
                           @ShellOption Integer authorId,
                           @ShellOption Integer genreId) {
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);
        Book book = new Book(id, name, year, author, genre);
        bookService.updateBook(book);
    }
}
