package cz.vibri.orderservice.repositories;

import cz.vibri.orderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);
}
