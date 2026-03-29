package cz.vibri.databases.repositories;

import cz.vibri.databases.domain.BookNatural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
