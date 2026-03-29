package cz.vibri.domain.repositories;

import cz.vibri.domain.joinedtable.ElectricGuitar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricGuitarRepository extends JpaRepository<ElectricGuitar, Long> {
}
