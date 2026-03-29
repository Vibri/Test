package cz.vibri.rest.service;

import cz.vibri.rest.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer findCustomerById(Long id);

    List<Customer> findAllCustomers();

    Customer saveCustomer(Customer customer);
}
