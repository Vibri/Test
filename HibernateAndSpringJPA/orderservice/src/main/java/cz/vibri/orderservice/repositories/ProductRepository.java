package cz.vibri.orderservice.repositories;

import cz.vibri.orderservice.domain.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByDescription(String description);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(Long aLong);
}
