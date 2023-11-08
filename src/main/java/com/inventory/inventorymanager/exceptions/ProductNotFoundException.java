package com.inventory.inventorymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for product-related errors.
 * Thrown when a product cannot be found in the database.
 * Requirement 9: Custom exception for when a product is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the Throwable.getMessage() method.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
