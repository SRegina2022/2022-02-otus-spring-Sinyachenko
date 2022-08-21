package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public Optional<Book> getBook(@ShellOption Integer id) {
        return bookService.getBook(id);
    }


    @ShellMethod("Create Book by (ID, Name, Year, AuthorId, GenreId)")
    public void insertBook(@ShellOption Integer id,
                        @ShellOption String name,
                        @ShellOption Integer year,
                        @ShellOption Integer authorId,
                        @ShellOption Integer genreId) {
        Author author = new Author(0, "dhfd");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        Genre genre = new Genre(0, "dfhdsdff");
        Book book = new Book(id, name, year, authors, genre);
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
        Author author = new Author(0, "dhfd");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        Genre genre = new Genre(0, "dfhdsdff");
        Book book = new Book(id, name, year, authors, genre);
        bookService.updateBook(book);
    }
}
