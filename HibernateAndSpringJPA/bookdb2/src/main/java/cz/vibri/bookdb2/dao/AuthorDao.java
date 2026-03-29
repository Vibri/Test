package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorDao {

    List<Author> findAll();

    List<Author> listAuthorByLastName(String lastName);

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    Author findAuthorByNameCriteria(String firstName, String lastName);

    Author findAuthorByNameNative(String firstName, String lastName);

    List<Author> findAuthorByLastNameOrderByFirstName(String lastName, Pageable pageable);
}
