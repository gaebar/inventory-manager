package com.inventory.inventorymanager.repository;

import com.inventory.inventorymanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    /**
     * Retrieves a product with the given name from the database.
     * @param name Name of the product.
     * @return Optional containing the found product or an empty Optional if not found.
     */
    Optional<Product> findByName(String name);

    /**
     * Checks if a product with the given productID exists in the database.
     * @param productID The ID of the product.
     * @return true if a product with the given productID exists, false otherwise.
     */
    boolean existsByProductId(Long productID);
}
