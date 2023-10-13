package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Fetches all products from the database.
     * @return List of all products.
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
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
     * Checks if a product with the same name already exists before adding a new one.
     * @param product The product to create.
     * @return The created product.
     */
    @Transactional
    public Product createProduct(Product product) {
        if(productRepository.existsByProductName(product.getProductName())) {
            throw new ProductAlreadyExistsException("Product with the name " + product.getProductName() + " already exists");
        }
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete.
     */
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

    /**
     * Updates a product by its ID.
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
