package cz.vibri.bookdb2;

import cz.vibri.bookdb2.dao.BookDao;
import cz.vibri.bookdb2.domain.Author;
import cz.vibri.bookdb2.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"cz.vibri.bookdb2.dao"})
public class BookDaoSpringJPATest {

    @Autowired
    BookDao bookDao;

    @Test
    void getById() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    void findBookByTitle() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
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
    void testFindAllBooks() {
        List<Book> books = bookDao.findAllBooks();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(5);
    }

    @Test
    void testFindAllBooksPage1() {
        List<Book> books = bookDao.findAllBooks(10, 0);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage2() {
        List<Book> books = bookDao.findAllBooks(10, 10);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage10() {
        List<Book> books = bookDao.findAllBooks(10, 100);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void testFindAllBooksPage1_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(0,10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage2_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(1,10)    );

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage10_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(10,10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void testFindAllBooksPage1_SortByTitle() {
        List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0,10,
                Sort.by(Sort.Order.desc("title"))));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }
}
