package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource source;

    public AuthorDaoImpl(DataSource source) {
        this.source = source;
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
        Connection connection = null;
//        Statement statement = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
//            statement = connection.createStatement();
            ps = connection.prepareStatement("SELECT * FROM author where id = ?");
            ps.setLong(1, id);
//            resultSet = statement.executeQuery("SELECT * FROM author where id = " + id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, ps, connection);
        }

        return null;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        return author;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        Connection connection = null;
//        Statement statement = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
//            statement = connection.createStatement();
            ps = connection.prepareStatement("SELECT * FROM author where first_name = ? and last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
//            resultSet = statement.executeQuery("SELECT * FROM author where id = " + id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, ps, connection);
        }

        return null;
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

    @Override
    public Author saveNewAuthor(Author author) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("INSERT INTO author (first_name, last_name) VALUES (?, ?)");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
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
    public Author updateAuthor(Author author) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("UPDATE author set first_name = ?, last_name = ? where author.id = ?");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setLong(3, author.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, ps, connection);
        }

        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = source.getConnection();
            ps = connection.prepareStatement("DELETE from author where id = ?");
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, connection);
        }
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Author> findAuthorByLastNameOrderByFirstName(String lastName, Pageable pageable) {
        return null;
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        return null;
    }
}
