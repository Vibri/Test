package cz.vibri.rest.bootstrap;

import cz.vibri.rest.domain.Customer;
import cz.vibri.rest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading customer data");

        Customer c1 = new Customer();
        c1.setFirstName("Michael");
        c1.setLastName("West");
        customerRepository.save(c1);

        Customer c2 = new Customer();
        c2.setFirstName("Michael");
        c2.setLastName("East");
        customerRepository.save(c2);

        Customer c3 = new Customer();
        c3.setFirstName("Fiona");
        c3.setLastName("Glen");
        customerRepository.save(c3);

        System.out.println("Customers saved: " + customerRepository.count());
    }
}
