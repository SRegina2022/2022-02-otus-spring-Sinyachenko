package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_NAME = "Idiot";
    private static final int EXISTING_BOOK_YEAR = 2000;
    private static final int EXISTING_BOOK_AUTORID = 1;
    private static final int EXISTING_BOOK_GENREID = 1;

    @Autowired
    private BookDaoJdbc bookDao;

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Autowired
    private GenreDaoJdbc genreDao;


    @BeforeTransaction
    void beforeTransaction(){
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction(){
        System.out.println("afterTransaction");
    }

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualBooksCount = bookDao.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    //@Rollback(value = false)
    //@Commit
    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expectedBook = new Book(EXPECTED_BOOKS_COUNT + 1, "Test",2001, authorDao.getById(1), genreDao.getById(1));
        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по ее id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,EXISTING_BOOK_YEAR,
                authorDao.getById(EXISTING_BOOK_AUTORID),
                genreDao.getById(EXISTING_BOOK_GENREID));
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по ее id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(new Book(1, "Idiot",2000,authorDao.getById(1),genreDao.getById(1)),
                        new Book(2, "Onegin",1980,authorDao.getById(2),genreDao.getById(1)),
                        new Book(3, "Romeo & Juliette",1990,authorDao.getById(3),genreDao.getById(2)));
    }

}
