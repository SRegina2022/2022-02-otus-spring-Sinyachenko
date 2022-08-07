package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;

import javax.validation.constraints.Null;


@ShellComponent
public class ShellLibraryDb {
    private final BookService bookService;

    @Autowired
    ShellLibraryDb(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Get count of books")
    public int countBook() {
        return bookService.count();
    }

    @ShellMethod("Get Book by ID")
    public Book getBook(@ShellOption Integer id) {
        return bookService.getBook(id);
    }


    @ShellMethod("Create Book by (ID, Name, Year, AuthorId, GenreId)")
    public void insertBook(@ShellOption Integer id,
                        @ShellOption String name,
                        @ShellOption Integer year,
                        @ShellOption Integer authorId,
                        @ShellOption Integer genreId) {
        Book book = new Book(id, name, year, authorId, genreId);
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
        Book book = new Book(id, name, year, authorId, genreId);
        bookService.updateBook(book);
    }
}
