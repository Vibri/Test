package cz.vibri.bookdb2;

import cz.vibri.bookdb2.dao.AuthorDao;
import cz.vibri.bookdb2.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"cz.vibri.bookdb2.dao"})
public class AuthorDaoSpringJPATest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testFindAuthorsPage1_SortByFirstName() {
        List<Author> authors = authorDao.findAuthorByLastNameOrderByFirstName("Smith", PageRequest.of(0,10,
                Sort.by(Sort.Order.desc("firstName"))));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Yugal");
    }

    @Test
    void testFindAuthorsPage2_SortByFirstName() {
        List<Author> authors = authorDao.findAuthorByLastNameOrderByFirstName("Smith", PageRequest.of(1,10,
                Sort.by(Sort.Order.desc("firstName"))));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Surendra");
    }
}
