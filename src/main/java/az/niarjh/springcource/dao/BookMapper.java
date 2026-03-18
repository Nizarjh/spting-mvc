package az.niarjh.springcource.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import az.niarjh.springcource.models.Book;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setAuthor(rs.getString("author"));
        book.setBookId((UUID) rs.getObject("bookId"));
        book.setOwnerId((UUID) rs.getObject("ownerId"));
        book.setTitle(rs.getString("title"));
        java.sql.Timestamp ts = rs.getTimestamp("publishDate");
        book.setPublishDate(ts != null ? ts.toLocalDateTime() : null);
        return book;
    }

}
