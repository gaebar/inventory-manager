package com.inventory.inventorymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for product-related errors.
 * Thrown when a product with the same name already exists in the database.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new ProductAlreadyExistsException with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the Throwable.getMessage() method.
     */
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
