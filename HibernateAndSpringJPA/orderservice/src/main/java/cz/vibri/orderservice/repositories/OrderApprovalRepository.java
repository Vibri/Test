package cz.vibri.orderservice.repositories;

import cz.vibri.orderservice.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
