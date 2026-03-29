package cz.vibri.orderservice;

import cz.vibri.orderservice.domain.OrderHeader;
import cz.vibri.orderservice.domain.Product;
import cz.vibri.orderservice.domain.ProductStatus;
import cz.vibri.orderservice.repositories.OrderHeaderRepository;
import cz.vibri.orderservice.repositories.ProductRepository;
import cz.vibri.orderservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {ProductService.class})
public class ProductRepositoryIT {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void testGetCategory() {
        Product product = productRepository.findByDescription("PRODUCT1").get();

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testSaveProduct() {

        Product product = new Product();
        product.setDescription("Product");
        product.setProductStatus(ProductStatus.NEW);

        Product saved = productRepository.save(product);

        assertNotNull(saved);
        assertNotNull(saved.getId());

        Product fetchedProduct = productRepository.getReferenceById(saved.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getId());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getLastModifiedDate());
        assertNotNull(fetchedProduct.getDescription());
        assertNotNull(fetchedProduct.getProductStatus());
    }

    @Test
    void testUpdateQuantityOnHand() {

        Product product = new Product();
        product.setDescription("Product");
        product.setProductStatus(ProductStatus.NEW);
        product.setQuantityOnHand(5);

        Product saved = productService.saveProduct(product);

        Product updatedProduct = productService.updateQOH(saved.getId(), 10);

        assertEquals(10, updatedProduct.getQuantityOnHand());
    }

}
