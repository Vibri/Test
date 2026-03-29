package cz.vibri.orderservice.services;

import cz.vibri.orderservice.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateQOH(Long id, Integer quantityOnHand);
}
