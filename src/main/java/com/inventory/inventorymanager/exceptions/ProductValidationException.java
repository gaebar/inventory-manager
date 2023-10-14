package com.inventory.inventorymanager.exceptions;

// Custom exception for product validation:
public class ProductValidationException extends RuntimeException {
    public ProductValidationException(String message) {
        super(message);
    }
}