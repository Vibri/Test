package cz.vibri.orderservice;

import cz.vibri.orderservice.domain.Address;
import cz.vibri.orderservice.domain.Customer;
import cz.vibri.orderservice.repositories.CustomerRepository;
import jakarta.persistence.Embedded;
import jakarta.persistence.Version;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryIT {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void getCustomer() {

        Customer customer = customerRepository.getReferenceById(1L);

        assertNotNull(customer);
        assertEquals(customer.getEmail(), "mail@mail.com");
        assertNotNull(customer.getOrderHeaders());
    }

    @Test
    void invalidCustomerName() {
        Customer customer = new Customer();
        customer.setCustomerName("012345678901234567890123456789012345678901234567890123456789");
        customerRepository.saveAndFlush(customer);

        Throwable exception = assertThrows(ConstraintViolationException.class, () -> customerRepository.saveAndFlush(customer));
        assertEquals("Validation failed for classes [cz.vibri.orderservice.domain.Customer] during persist time for groups [jakarta.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='size must be between 0 and 50', propertyPath=customerName, rootBeanClass=class cz.vibri.orderservice.domain.Customer, messageTemplate='{jakarta.validation.constraints.Size.message}'}\n" +
                "]", exception.getMessage());
    }

    @Test
    void invalidEmail() {
        Customer customer = new Customer();
        customer.setEmail("012345678901234567890123456789012345678901234567890123456789");

        Throwable exception = assertThrows(ConstraintViolationException.class, () -> customerRepository.saveAndFlush(customer));
        assertEquals("Validation failed for classes [cz.vibri.orderservice.domain.Customer] during persist time for groups [jakarta.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='must be a well-formed email address', propertyPath=email, rootBeanClass=class cz.vibri.orderservice.domain.Customer, messageTemplate='{jakarta.validation.constraints.Email.message}'}\n" +
                "]", exception.getMessage());
    }

    @Test
    void validEmail() {
        Customer customer = new Customer();
        customer.setEmail("info@email.com");

        assertDoesNotThrow(() -> customerRepository.saveAndFlush(customer));

    }

    @Test
    void invalidAddress() {
        Address address = new Address();
        address.setAddress("012345678901234567890123456789012345678901234567890123456789");

        Customer customer = new Customer();
        customer.setCustomerAddress(address);

        Throwable exception = assertThrows(ConstraintViolationException.class, () -> customerRepository.saveAndFlush(customer));
        assertEquals("Validation failed for classes [cz.vibri.orderservice.domain.Address] during persist time for groups [jakarta.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='size must be between 0 and 30', propertyPath=customerAddress.address, rootBeanClass=class cz.vibri.orderservice.domain.Customer, messageTemplate='{jakarta.validation.constraints.Size.message}'}\n" +
                "]", exception.getMessage());
    }

    @Test
    void invalidCity() {
        Address address = new Address();
        address.setCity("012345678901234567890123456789012345678901234567890123456789");

        Customer customer = new Customer();
        customer.setCustomerAddress(address);

        Throwable exception = assertThrows(ConstraintViolationException.class, () -> customerRepository.saveAndFlush(customer));
        assertEquals("Validation failed for classes [cz.vibri.orderservice.domain.Address] during persist time for groups [jakarta.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='size must be between 0 and 30', propertyPath=customerAddress.city, rootBeanClass=class cz.vibri.orderservice.domain.Customer, messageTemplate='{jakarta.validation.constraints.Size.message}'}\n" +
                "]", exception.getMessage());
    }

    @Test
    void invalidState() {
        Address address = new Address();
        address.setState("012345678901234567890123456789012345678901234567890123456789");

        Customer customer = new Customer();
        customer.setCustomerAddress(address);

        Throwable exception = assertThrows(ConstraintViolationException.class, () -> customerRepository.saveAndFlush(customer));
        assertEquals("Validation failed for classes [cz.vibri.orderservice.domain.Address] during persist time for groups [jakarta.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='size must be between 0 and 30', propertyPath=customerAddress.state, rootBeanClass=class cz.vibri.orderservice.domain.Customer, messageTemplate='{jakarta.validation.constraints.Size.message}'}\n" +
                "]", exception.getMessage());
    }

    @Test
    void invalidZipCode() {
        Address address = new Address();
        address.setZipCode("012345678901234567890123456789012345678901234567890123456789");

        Customer customer = new Customer();
        customer.setCustomerAddress(address);

        Throwable exception = assertThrows(ConstraintViolationException.class, () -> customerRepository.saveAndFlush(customer));
        assertEquals("Validation failed for classes [cz.vibri.orderservice.domain.Address] during persist time for groups [jakarta.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='size must be between 0 and 30', propertyPath=customerAddress.zipCode, rootBeanClass=class cz.vibri.orderservice.domain.Customer, messageTemplate='{jakarta.validation.constraints.Size.message}'}\n" +
                "]", exception.getMessage());
    }
}
