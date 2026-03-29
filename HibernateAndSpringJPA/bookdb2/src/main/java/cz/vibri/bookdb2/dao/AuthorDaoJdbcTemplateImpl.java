package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Author;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@Primary
public class AuthorDaoJdbcTemplateImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public List<Author> listAuthorByLastName(String lastName) {
        return null;
    }

    @Override
    public Author getById(Long id) {
        String sql = "select author.id as id, first_name, last_name, book.id as book_id, book.isbn, book.publisher, book.title from author\n" +
                "left outer join book on author.id = book.author_id where author.id = ?";

//        return jdbcTemplate.queryForObject("SELECT * FROM author where id = ?", getRowMapper(), id);
        return jdbcTemplate.query(sql, new AuthorExtractor(), id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("SELECT * FROM author where first_name = ? and last_name = ?", getRowMapper(), firstName, lastName);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO author (first_name, last_name) VALUES (?, ?)", author.getFirstName(), author.getLastName());

        Long createdId = jdbcTemplate.  queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(createdId);
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update("UPDATE author SET first_name = ?, last_name = ? where id = ?", author.getFirstName(), author.getLastName(), author.getId() );

        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        jdbcTemplate.update("DELETE FROM author WHERE id = ?", id);
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Author> findAuthorByLastNameOrderByFirstName(String lastName, Pageable pageable) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from author WHERE last_name = ? ");
        if (pageable.getSort().getOrderFor("first_name") != null) {
            sb.append("order by first_name ").append(pageable.getSort().getOrderFor("first_name").getDirection().name());
        }
        sb.append(" limit ? offset ?");

        System.out.println(sb.toString());
        return jdbcTemplate.query(sb.toString(),getRowMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
    }

    private RowMapper<Author> getRowMapper() {
        return new AuthorMapper();
    }
}
