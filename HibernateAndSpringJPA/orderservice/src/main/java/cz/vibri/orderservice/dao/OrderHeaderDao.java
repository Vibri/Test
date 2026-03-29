package cz.vibri.orderservice.dao;

import cz.vibri.orderservice.domain.OrderHeader;
import cz.vibri.orderservice.repositories.OrderHeaderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class OrderHeaderDao {

    private final OrderHeaderRepository orderHeaderRepository;

    public OrderHeaderDao(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    public OrderHeader saveNewOrderHeader(OrderHeader orderHeader) {
        return orderHeaderRepository.save(orderHeader);
    }
}
