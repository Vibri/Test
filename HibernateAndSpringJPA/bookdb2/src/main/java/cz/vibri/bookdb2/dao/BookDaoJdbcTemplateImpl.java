package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@Primary
public class BookDaoJdbcTemplateImpl implements BookDao{

    private final JdbcTemplate jdbcTemplate;
    private final AuthorDao authorDao;

    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate, AuthorDao authorDao) {
        this.authorDao = authorDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book findByISBN(String isbn) {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book where id = ?", getRowMapper(), id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM book where title = ?", getRowMapper(), title);
    }

    @Override
    public Book saveNewBook(Book book) {
        Long authorId = null;
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            authorId = book.getAuthor().getId();
        }
        jdbcTemplate.update("INSERT INTO book (isbn, publisher, title, author_id) VALUES (?, ?, ?, ?)", book.getIsbn(), book.getPublisher(), book.getTitle(), authorId);

        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(createdId);
    }

    @Override
    public Book updateBook(Book book) {
        Long authorId = null;
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            authorId = book.getAuthor().getId();
        }
        jdbcTemplate.update("UPDATE book set isbn = ?, title = ?, publisher = ?, author_id = ? where id = ?", book.getIsbn(), book.getTitle(), book.getPublisher(), authorId, book.getId());

        return this.getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book findBookByTitleCriteria(String title) {
            return null;
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        String sql = "SELECT * FROM book order by title " + pageable.getSort().getOrderFor("title").getDirection().name()
                + " limit ? offset ?";

        System.out.println(sql);
        return jdbcTemplate.query(sql,getRowMapper(), pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM book limit ? offset ?", getRowMapper(), pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return jdbcTemplate.query("SELECT * FROM book limit ? offset ?", getRowMapper(), pageSize, offset);
    }

    @Override
    public List<Book> findAllBooks() {
        return jdbcTemplate.query("SELECT * FROM book", getRowMapper());
    }

    private RowMapper<Book> getRowMapper() {
        return new BookMapper(authorDao);
    }
}
