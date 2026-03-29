package cz.vibri.databases.repositories;

import cz.vibri.databases.domain.composite.AuthorComposite;
import cz.vibri.databases.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
