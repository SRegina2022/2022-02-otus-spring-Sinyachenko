DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
                    ID BIGINT PRIMARY KEY,
                    NAME VARCHAR(255),
                    PUB_YEAR INT,
                    AUTHOR_ID BIGINT,
                    GENRE_ID BIGINT
                  );