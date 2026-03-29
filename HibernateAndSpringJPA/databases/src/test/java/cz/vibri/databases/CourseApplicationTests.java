package cz.vibri.databases;

import cz.vibri.databases.repositories.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseApplicationTests {

	@Autowired
	BookRepository bookRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testBookRepository() {
		long count = bookRepository.count();
		Assertions.assertThat(count).isGreaterThan(0);
	}
}
