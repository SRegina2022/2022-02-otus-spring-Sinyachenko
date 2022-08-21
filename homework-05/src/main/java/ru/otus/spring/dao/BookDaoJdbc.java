package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        // Это просто оставили, чтобы не переписывать код
        // В идеале всё должно быть на NamedParameterJdbcOperations
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;

        authorDao = new AuthorDaoJdbc(namedParameterJdbcOperations);
        genreDao = new GenreDaoJdbc(namedParameterJdbcOperations);
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from books", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Book book) {
        final Map<String, Object> params = new HashMap<>(5);
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("pub_year", book.getYear());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());

        namedParameterJdbcOperations.update("insert into books (id, name, pub_year, author_id, genre_id) values (:id, :name, :pub_year, :author_id, :genre_id)",
                params);
    }

    @Override
    public void update(Book book) {
        final Map<String, Object> params = new HashMap<>(2);
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("pub_year", book.getYear());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());

        namedParameterJdbcOperations.update("update books set name=:name, pub_year=:pub_year, author_id=:author_id, genre_id=:genre_id where id=:id",
                params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id, b.name, pub_year, author_id, genre_id, " +
                        "a.name as author_name, g.name as genre_name " +
                        "from books b, authors a, genres g " +
                        "where b.id = :id and a.id = b.author_id and g.id = b.genre_id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select b.id, b.name, pub_year, author_id, genre_id, " +
                        "a.name as author_name, g.name as genre_name " +
                        "from books b, authors a, genres g " +
                        "where a.id = b.author_id and g.id = b.genre_id", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int year = resultSet.getInt("pub_year");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            String authorName = resultSet.getString("author_name");
            String genreName = resultSet.getString("genre_name");

            Author author = new Author(authorId, authorName);
            Genre genre = new Genre(genreId, genreName);

            return new Book(id, name, year, author, genre);
        }
    }
}
