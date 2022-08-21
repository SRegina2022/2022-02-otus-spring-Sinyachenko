package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Drama";


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

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        int actualGenresCount = genreDao.count();
        assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    //@Rollback(value = false)
    //@Commit
    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre() {
        Genre expectedGenre = new Genre(EXPECTED_GENRES_COUNT + 1, "Fantasy");
        genreDao.insert(expectedGenre);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteGenreById() {
        assertThatCode(() -> genreDao.getById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();

        genreDao.deleteById(EXISTING_GENRE_ID);

        assertThatThrownBy(() -> genreDao.getById(EXISTING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        List<Genre> actualGenreList = genreDao.getAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(new Genre(1, "Drama"),
                        new Genre(2, "Comedy"));
    }

}