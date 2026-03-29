package cz.vibri.orderservice.bootstrap;

import cz.vibri.orderservice.domain.Customer;
import cz.vibri.orderservice.domain.OrderHeader;
import cz.vibri.orderservice.domain.Product;
import cz.vibri.orderservice.domain.ProductStatus;
import cz.vibri.orderservice.repositories.CustomerRepository;
import cz.vibri.orderservice.repositories.OrderHeaderRepository;
import cz.vibri.orderservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductService productService;

    private void updateProduct() {
        Product product = new Product();
        product.setDescription("Product");
        product.setProductStatus(ProductStatus.NEW);
        product.setQuantityOnHand(5);

        Product saved = productService.saveProduct(product);

        Product updatedProduct = productService.updateQOH(saved.getId(), 10);

        System.out.println("Updated Qty: " + updatedProduct.getQuantityOnHand());
    }


    @Override
    public void run(String... args) throws Exception {

        updateProduct();

        bootstrapOrderService.readOrderData();


        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");

        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Version is: " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("Version is: " + savedCustomer2.getVersion());

        savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        System.out.println("Version is: " + savedCustomer3.getVersion());

        customerRepository.delete(savedCustomer3);
    }
}
