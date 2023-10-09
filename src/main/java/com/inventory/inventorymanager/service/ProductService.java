package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Contains business logic for handling products, and talks to the ProductRepository for database interactions.
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();

    }
}
