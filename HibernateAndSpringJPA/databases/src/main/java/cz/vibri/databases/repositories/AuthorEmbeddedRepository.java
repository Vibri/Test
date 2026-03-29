package cz.vibri.databases.repositories;

import cz.vibri.databases.domain.composite.AuthorEmbedded;
import cz.vibri.databases.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
