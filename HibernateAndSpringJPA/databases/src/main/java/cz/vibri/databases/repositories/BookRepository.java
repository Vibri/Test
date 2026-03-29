package cz.vibri.databases.repositories;

import cz.vibri.databases.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
