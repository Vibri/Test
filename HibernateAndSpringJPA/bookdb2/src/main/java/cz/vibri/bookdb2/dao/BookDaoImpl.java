package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Author;
import cz.vibri.bookdb2.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    private final DataSource source;
    private final AuthorDao authorDao;

    public BookDaoImpl(DataSource source, AuthorDao authorDao) {
        this.source = source;
        this.authorDao = authorDao;
    }

    @Override
    public Book findByISBN(String isbn) {
        return null;
    }

    @Override
    public Book getById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book where id = ?");
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, ps, connection);
        }

        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book where title = ?");
            ps.setString(1, title);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, ps, connection);
        }

        return null;
    }

    @Override
    public Book saveNewBook(Book book) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("INSERT INTO book (isbn, publisher, title, author_id) VALUES (?, ?, ?, ?)");
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getPublisher());
            ps.setString(3, book.getTitle());
            if (book.getAuthor() != null) {
                ps.setLong(4, book.getAuthor().getId());
            } else {
                ps.setNull(4, -5);
            }

            ps.execute();

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                Long savedId = resultSet.getLong(1);
                return this.getById(savedId);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, ps, connection);
        }

        return null;

    }

    @Override
    public Book updateBook(Book book) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("UPDATE book set isbn = ?, title = ?, publisher = ?, author_id = ? where id = ?");
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getPublisher());
            if (book.getAuthor() != null) {
                ps.setLong(4, book.getAuthor().getId());
            }
            ps.setLong(5, book.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, ps, connection);
        }

        return this.getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("DELETE from book where id = ?");
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, connection);
        }
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
        return null;
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return null;
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setTitle(resultSet.getString("title"));

        book.setAuthor(authorDao.getById(resultSet.getLong("author_id")));

        return book;
    }

    private void closeAll(ResultSet resultSet, PreparedStatement ps, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
//                    if (statement != null) {
//                        statement.close();
//                    }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }

            }
        }
    }

}
