insert into authors (id, `name`) values (1, 'Dostoevskiy');
insert into authors (id, `name`) values (2, 'Pushkin');
insert into authors (id, `name`) values (3, 'Sheckspear');

insert into genres (id, `name`) values (1, 'Drama');
insert into genres (id, `name`) values (2, 'Comedy');

insert into books (id, `name`, `pub_year`, `author_id`, `genre_id`) values (1, 'Idiot', 2000, 1, 1);
insert into books (id, `name`, `pub_year`, `author_id`, `genre_id`) values (2, 'Onegin', 1980, 2, 1);
insert into books (id, `name`, `pub_year`, `author_id`, `genre_id`) values (3, 'Romeo & Juliette', 1990, 3, 2);

