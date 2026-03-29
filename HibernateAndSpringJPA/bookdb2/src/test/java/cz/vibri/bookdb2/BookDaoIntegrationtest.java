package cz.vibri.bookdb2;

import cz.vibri.bookdb2.dao.AuthorDao;
import cz.vibri.bookdb2.dao.BookDao;
import cz.vibri.bookdb2.domain.Author;
import cz.vibri.bookdb2.domain.Book;
import jakarta.persistence.EntityNotFoundException;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"cz.vibri.bookdb2.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationtest {

    @Autowired
    BookDao bookDao;

    @Test
    void testGetBook() {

        Book book = bookDao.getById(1L);

        assertThat(book).isNotNull();
    }

    @Test
    void testFindBookByTitle() {

        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
        assertThat(book.getPublisher()).isEqualTo("Addison Wesley");
        assertThat(book.getIsbn()).isEqualTo("978-0134494166");
    }

    @Test
    void testSaveBook() {

        Book book = new Book();
        Author author = new Author();
        author.setId(3l);

        book.setAuthor(author);
        book.setIsbn("978-1111111111");
        book.setPublisher("Vibri");
        book.setTitle("Java for dummies");

        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testUpdateBook() {

        Book book = new Book();

        Author author = new Author();
        author.setId(3l);

        book.setAuthor(author);
        book.setIsbn("978-1111111111");
        book.setPublisher("Vi");
        book.setTitle("Java");

        Book saved = bookDao.saveNewBook(book);

        saved.setPublisher("Vibri");
        Book updated = bookDao.updateBook(saved);
        assertThat(updated.getPublisher()).isEqualTo("Vibri");
    }

    @Test
    void testDeleteBook() {

        Book book = new Book();
        book.setIsbn("978-1111111112");
        book.setPublisher("Vibri");
        book.setTitle("Java deleting");

        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Book deleted = bookDao.getById(saved.getId());
        });

    }

    @Test
    void testFindBookByISBN() {
        Book book = new Book();
        book.setIsbn("1234" + RandomString.make());
        book.setTitle("ISBN TEST");

        Book saved = bookDao.saveNewBook(book);

        Book fetched = bookDao.findByISBN(book.getIsbn());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testFindAllBooks() {
        List<Book> books = bookDao.findAll();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBookByTitleCriteria() {

        Book book = bookDao.findBookByTitleCriteria("Clean Code");

        assertThat(book).isNotNull();
        assertThat(book.getPublisher()).isEqualTo("Addison Wesley");
        assertThat(book.getIsbn()).isEqualTo("978-0134494166");

    }

    @Test
    void testFindBookByTitleNotFound() {
        assertThrows(EntityNotFoundException.class, () -> {
            Book book = bookDao.findBookByTitle("foo");
        });
    }

}
