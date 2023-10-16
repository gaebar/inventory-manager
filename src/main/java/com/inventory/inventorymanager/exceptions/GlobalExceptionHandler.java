package com.inventory.inventorymanager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler serves as a centralized exception handling mechanism for all controllers.
 * It catches exceptions and returns appropriate HTTP response statuses along with logging.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** Logger instance for logging exception details. */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles ProductNotFoundException by logging the error and returning a 404 NOT FOUND status.
     *
     * @param exception the thrown ProductNotFoundException
     * @return ResponseEntity with 404 status and error message
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception) {
        logger.error("Product not found: {}", exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles ProductAlreadyExistsException by logging the error and returning a 409 CONFLICT status.
     *
     * @param exception the thrown ProductAlreadyExistsException
     * @return ResponseEntity with 409 status and error message
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductAlreadyExistsException(ProductAlreadyExistsException exception) {
        logger.error("Product already exists: {}", exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * A catch-all handler for all other exceptions. Logs the error and returns a 500 INTERNAL SERVER ERROR status.
     *
     * @param exception the thrown exception
     * @return ResponseEntity with 500 status and error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception exception) {
        logger.error("An error occurred: {}", exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
