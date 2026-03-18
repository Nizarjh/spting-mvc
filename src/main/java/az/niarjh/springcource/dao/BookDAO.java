package az.niarjh.springcource.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import az.niarjh.springcource.models.Book;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Book> showAllBooks() {
        return jdbcTemplate.query("SELECT * FROM book",
                new BookMapper());
    }

    public Book showBook(UUID id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE bookId=?",
                new BookMapper(),
                id);
    }

    public List<Book> showBooksByPerson(UUID ownerId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE ownerId=?",
                new BeanPropertyRowMapper<>(Book.class),
                ownerId);
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(title,author,publishDate) VALUES(?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getPublishDate());
    }

    public void updateBook(Book updatedbook, UUID id) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?,publishDate=? ,ownerid=? WHERE bookid=?",
                updatedbook.getTitle(),
                updatedbook.getAuthor(),
                updatedbook.getPublishDate(),
                updatedbook.getOwnerId(),
                id);
    }

    public void deleteBook(UUID id) {
        jdbcTemplate.update("DELETE FROM book WHERE bookId=?",
                id);
    }

    public void freeBook(UUID id) {
        jdbcTemplate.update("UPDATE Book SET ownerId = NULL  WHERE bookId = ?",
                id);
    }

}