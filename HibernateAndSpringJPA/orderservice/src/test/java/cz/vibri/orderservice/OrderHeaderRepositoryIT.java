package cz.vibri.orderservice;

import cz.vibri.orderservice.dao.OrderHeaderDao;
import cz.vibri.orderservice.domain.*;
import cz.vibri.orderservice.repositories.CustomerRepository;
import cz.vibri.orderservice.repositories.OrderApprovalRepository;
import cz.vibri.orderservice.repositories.OrderHeaderRepository;
import cz.vibri.orderservice.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryIT {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    Product product;

    @BeforeEach
    void setUp() {
        Product newProduct = new Product();
        newProduct.setDescription("test product");
        product = productRepository.saveAndFlush(newProduct);
    }


    @Test
    void testSaveOrderWithLine() {
        OrderHeader orderHeader = new OrderHeader();

        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        customer.addOrderHeader(orderHeader);

        customerRepository.save(customer);


        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);

//        orderHeader.setOrderLines(Set.of(orderLine));
//        orderLine.setOrderHeader(orderHeader);
        orderHeader.addOrderLine(orderLine);

        orderLine.setProduct(product);

        OrderApproval approval = new OrderApproval();
        approval.setApprovedBy("me");
        orderHeader.setOrderApproval(approval);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        orderHeaderRepository.flush();

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(savedOrder.getOrderLines().size(), 1);
    }

    @Test
    void testSaveOrder() {

        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        customer.setPhone("0123456789");

        Address address = new Address();
        address.setCity("0123456789");
        customer.setCustomerAddress(address);
        customer.addOrderHeader(orderHeader);

        customerRepository.save(customer);

        OrderHeader saved = orderHeaderRepository.save(orderHeader);

        assertNotNull(saved);
        assertNotNull(saved.getId());

        OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(saved.getId());

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
        assertNotNull(fetchedOrder.getLastModifiedDate());
    }

    @Test
    void testDeleteCascade() {
        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName("new Customer");
        orderHeader.setCustomer(customerRepository.save(customer));

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(3);
        orderLine.setProduct(product);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("me");
        orderHeader.setOrderApproval(orderApproval);

        orderHeader.addOrderLine(orderLine);
        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);

        System.out.println("order saved and flushed");

        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();

        assertThrows(EntityNotFoundException.class, () -> {
            OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());

            assertNotNull(fetchedOrder.getBillToAddress());
        });

    }
}
