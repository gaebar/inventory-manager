package com.inventory.inventorymanager.controller;

import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles incoming HTTP requests related to products and routes them
 * to the appropriate service methods.
 */
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductService productService;

    /**
     * Constructs a ProductController with the specified ProductService.
     * @param productService The service to manage products.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves a list of all products.
     * @return A ResponseEntity containing the list of products and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product to retrieve.
     * @return A ResponseEntity containing the product and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Creates a new product.
     * @param product The product to create.
     * @return A ResponseEntity containing the created product and HTTP status.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete.
     * @return A ResponseEntity with an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates a product by its ID.
     * @param id The ID of the product to update.
     * @param product The new details of the product.
     * @return A ResponseEntity containing the updated product and HTTP status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
