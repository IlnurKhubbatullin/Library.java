package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.maxima.springmvc.models.Book;
import ru.maxima.springmvc.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return  jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(name, author, age)  values( ?, ?, ?)", book.getName(), book.getAuthor(),
                book.getAge());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("update book set name = ?, author=?, age = ? where id = ?", updateBook.getName(), updateBook.getAuthor(),
                updateBook.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }


    public void toGiveBook(Book book, Person person ) {
        jdbcTemplate.update("update book set owner=? where id=?", book.getOwner(), person.getId());
    }

}
