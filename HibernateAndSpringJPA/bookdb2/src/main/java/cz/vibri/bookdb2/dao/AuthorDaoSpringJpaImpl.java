package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Author;
import cz.vibri.bookdb2.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class AuthorDaoSpringJpaImpl implements AuthorDao{

    private final AuthorRepository authorRepository;

    public AuthorDaoSpringJpaImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
        return authorRepository.getReferenceById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author foundAuthor = authorRepository.getReferenceById(author.getId());
        foundAuthor.setFirstName(author.getFirstName());
        foundAuthor.setLastName(author.getLastName());
        return authorRepository.save(foundAuthor);
    }

    @Override
    public List<Author> findAuthorByLastNameOrderByFirstName(String lastName, Pageable pageable) {
        return authorRepository.findAuthorByLastName(lastName, pageable).getContent();
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);

    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        return null;
    }
}
