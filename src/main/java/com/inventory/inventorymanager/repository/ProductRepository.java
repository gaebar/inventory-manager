package com.inventory.inventorymanager.repository;

import com.inventory.inventorymanager.model.Product;
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

    /**
     * Retrieves products where stock is below the minimum threshold.
     * @return List of products with stock below the minimum threshold.
     */
    List<Product> findByCurrentStockLessThan(Integer threshold);

    /**
     * Retrieves a list of products where the current stock is less than the specified value
     * and the product ID matches the given ID.
     *
     * @param currentStock The threshold stock value.
     * @param id The product ID.
     * @return List of products matching the criteria.
     */
    List<Product> findByCurrentStockLessThanAndProductID(Integer currentStock, Long id);

    /**
     * Checks if a product with the given productID exists in the database.
     * @param productID The ID of the product.
     * @return true if a product with the given productID exists, false otherwise.
     */
    boolean existsByProductID(Long productID);


   //

//    @Query("SELECT p FROM Product p WHERE p.expiryDate < :calculatedDate")
    @Query(nativeQuery = true, value ="SELECT * FROM products WHERE DATE_SUB(expiry_date, INTERVAL time_duration_for_markdown DAY) < :today")
    List<Product> findPastMarkdownDate(@Param("today") LocalDate calculatedDate);

    @Query(nativeQuery = true, value ="SELECT * FROM products WHERE DATE_SUB(expiry_date, INTERVAL time_duration_for_markdown DAY) between :today  and DATE_ADD(:today, INTERVAL 7 DAY)")
    List<Product> findForMarkDownWithinWeek(@Param("today") LocalDate calculatedDate);

}

