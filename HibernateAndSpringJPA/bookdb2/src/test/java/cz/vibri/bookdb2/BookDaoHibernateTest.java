package cz.vibri.bookdb2;

import cz.vibri.bookdb2.dao.AuthorDao;
import cz.vibri.bookdb2.dao.BookDao;
import cz.vibri.bookdb2.dao.BookDaoHibernateImpl;
import cz.vibri.bookdb2.dao.BookDaoJdbcTemplateImpl;
import cz.vibri.bookdb2.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"cz.vibri.bookdb2.dao"})
public class BookDaoHibernateTest {
    @Autowired
    EntityManagerFactory emf;

    BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDaoHibernateImpl(emf);
    }

    @Test
    void testFindAllBooksPage1() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(0,10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage1_SortByTitle() {
        List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0,10,
                Sort.by(Sort.Order.desc("title"))));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }
}
