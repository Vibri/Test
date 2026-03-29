package cz.vibri.bookdb2;

import cz.vibri.bookdb2.dao.AuthorDao;
import cz.vibri.bookdb2.domain.Author;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"cz.vibri.bookdb2.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthor() {

        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull();
    }

    @Test
    void testFindAuthorByName() {

        Author author = authorDao.findAuthorByName("Robert", "Martin");

        assertThat(author).isNotNull();
        assertThat(author.getFirstName()).isEqualTo("Robert");
        assertThat(author.getLastName()).isEqualTo("Martin");
    }

    @Test
    void testSaveAuthor() {

        Author author = new Author();
        author.setFirstName("Luck");
        author.setLastName("Lucky");

        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testUpdateAuthor() {

        Author author = new Author();
        author.setFirstName("Luck");
        author.setLastName("L222");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Luckyly");
        Author updated = authorDao.updateAuthor(saved);
        assertThat(updated.getLastName()).isEqualTo("Luckyly");
    }

    @Test
    void testDeleteAuthor() {

        Author author = new Author();
        author.setFirstName("Luck");
        author.setLastName("L");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Author deleted = authorDao.getById(saved.getId());
        });

    }

    @Test
    void testListAuthorByLastNameLike() {
        List<Author> authors = authorDao.listAuthorByLastName("Wall");

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void testFindAllAuthors() {
        List<Author> authors = authorDao.findAll();

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void testFindAuthorByNameNative() {
        Author author = authorDao.findAuthorByNameNative("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    void testFindAuthorByNameNotFound() {
        assertThrows(EntityNotFoundException.class, () -> {
            Author author = authorDao.findAuthorByName("foo", "bar");
        });
    }
}
