package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.inventory.inventorymanager.model.Notification;
import com.inventory.inventorymanager.repository.NotificationRepository;

import java.util.List;
import java.time.LocalDateTime;

/**
 * Service class responsible for handling products.
 * This class talks to the ProductRepository for database interactions.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;


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
    public Product createProduct(Product product) {
        if(productRepository.existsByProductName(product.getProductName())) {
            throw new IllegalArgumentException("Product with the same name already exists");
        }
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
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

    /**
     * Checks if the inventory for a product has reached its defined thresholds.
     * If so, sends a notification and saves it in the database.
     * @param product The product to check.
     */
    public void checkAndUpdateInventory(Product product) {
        int currentStock = product.getCurrentStock();
        int minThreshold = product.getMinThreshold();
        int maxThreshold = product.getMaxThreshold();

        String message = null;

        if (currentStock <= minThreshold) {
            message = "Inventory low: Replenish product " + product.getProductName();
        } else if (currentStock >= maxThreshold) {
            message = "Inventory high: No replenishment needed for " + product.getProductName();
        }

        if (message != null) {
            notificationService.sendInventoryNotification(product, message);
        }
    }


    /**
     * Checks the inventory status of all products in the database.
     * Loops through each product and calls checkAndUpdateInventory method.
     */
    public void checkAllProductsInventory() {
        List<Product> products = getProducts();
        for(Product product : products) {
            checkAndUpdateInventory(product);
        }
    }
}
