package cz.vibri.orderservice.repositories;

import cz.vibri.orderservice.domain.Customer;
import cz.vibri.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    List<OrderHeader> findAllByCustomer(Customer customer);
}
