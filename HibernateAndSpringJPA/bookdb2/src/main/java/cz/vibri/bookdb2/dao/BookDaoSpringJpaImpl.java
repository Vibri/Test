package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Book;
import cz.vibri.bookdb2.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class BookDaoSpringJpaImpl implements BookDao{

    private final BookRepository bookRepository;

    public BookDaoSpringJpaImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findByISBN(String isbn) {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        Book foundBook = bookRepository.getReferenceById(book.getId());

        foundBook.setIsbn(book.getIsbn());
        foundBook.setPublisher(book.getPublisher());
        foundBook.setTitle(book.getTitle());

        foundBook.setAuthor(book.getAuthor());
        return bookRepository.save(foundBook);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
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
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage.getContent();
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        Pageable pageable = PageRequest.ofSize(pageSize);

        if (offset > 0) {
            pageable = pageable.withPage(offset / pageSize);
        } else {
            pageable = pageable.withPage(0);
        }
        return findAllBooks(pageable);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
