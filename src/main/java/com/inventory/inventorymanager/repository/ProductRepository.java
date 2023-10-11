package com.inventory.inventorymanager.repository;

import com.inventory.inventorymanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Product CRUD operations.
 * This extends JpaRepository to leverage Spring JPA's methods for CRUD operations.
 *
 * Requirement 6: Interface for performing CRUD operations on the product data.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Checks if a product with the given name exists in the database.
     * @param productName The name of the product.
     * @return true if a product with the given name exists, false otherwise.
     */
    boolean existsByProductName(String productName);
}
