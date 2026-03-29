package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Author;
import cz.vibri.bookdb2.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    private AuthorDao authorDao;

    public BookMapper(final AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setPublisher(rs.getString("publisher"));
        Long authorId = rs.getLong("author_id");
        if (authorId != null && authorId > 0) {
            book.setAuthor(authorDao.getById(authorId));
        }

        return book;
    }
}
