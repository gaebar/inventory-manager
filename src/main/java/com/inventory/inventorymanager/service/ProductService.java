package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling products.
 * This class talks to the ProductRepository for database interactions.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Fetches all products from the database.
     * Requirement 1: Read a list of products from the database and return them.
     * @return List of all products.
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
     * Requirement 3: Find a product by its ID.
     * @param id The ID of the product to find.
     * @return The product found.
     * @throws ProductNotFoundException if product is not found.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
    }

    /**
     * Creates a new product.
     * Requirement 2: Check if a product with the same name already exists before adding a new one.
     * @param product The product to create.
     * @return The created product.
     */
    public Product createProduct(Product product) {
        if(productRepository.existsByProductName(product.getProductName())) {
            throw new IllegalArgumentException("Product with the same name already exists");
        }
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     * Requirement 4: Throw an exception if trying to delete a non-existing product.
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

    /**
     * Updates a product by its ID.
     * Requirement 5: Update a product by its ID.
     * @param id The ID of the product to update.
     * @param newProduct The new product details.
     * @return The updated product.
     * @throws ProductNotFoundException if product is not found.
     */
    @Transactional
    public Product updateProduct(Long id, Product newProduct) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        newProduct.setProductID(id);
        return productRepository.save(newProduct);
    }
}
