package cz.vibri.bookdb2;

import cz.vibri.bookdb2.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        System.out.println("Count: " + countBefore);
        assertThat(countBefore).isGreaterThan(0);
    }
}
