package com.inventory.inventorymanager.exceptions;

/**
 * Custom exception for product-related errors.
 * Thrown when a product cannot be found in the database.
 * Requirement 9: Custom exception for when a product is not found.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the Throwable.getMessage() method.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ProductNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message, saved for later retrieval by the Throwable.getMessage() method.
     * @param cause The cause of the exception.
     */
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
