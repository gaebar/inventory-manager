package com.inventory.inventorymanager.repository;

import com.inventory.inventorymanager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * Repository interface for Product CRUD operations.
 * This extends JpaRepository to leverage Spring JPA methods for CRUD operations.

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
     * @param productName Name of the product.
     * @return Optional containing the found product or an empty Optional if not found.
     */
    Optional<Product> findByProductName(String productName);

    /**
     * Retrieves a list of products that are about to expire.
     * @param date The date to check against.
     * @return List of products expiring on or before the given date.
     */
    List<Product> findByExpiryDateBefore(LocalDate date);


    @Query(nativeQuery = true, value ="SELECT * FROM products WHERE DATE_SUB(expiry_date, INTERVAL time_duration_for_markdown DAY) < :today")
    List<Product> findPastMarkdownDate(@Param("today") LocalDate calculatedDate);

    @Query(nativeQuery = true, value ="SELECT * FROM products WHERE DATE_SUB(expiry_date, INTERVAL time_duration_for_markdown DAY) between :today  and DATE_ADD(:today, INTERVAL 7 DAY)")
    List<Product> findForMarkDownWithinWeek(@Param("today") LocalDate calculatedDate);

}

