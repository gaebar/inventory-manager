package com.inventory.inventorymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A global exception handler for centralized exception handling across all the controllers.
 * This handler provides a consistent style to return responses in case of errors.
 * <p>
 * Annotated with {@link ControllerAdvice} to provide centralized exception handling
 * across all `@RequestMapping` methods.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@link ProductNotFoundException}.
     *
     * @param exception the exception that was thrown.
     * @return a ResponseEntity with a NOT FOUND status and the exception's message.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions of type {@link ProductAlreadyExistsException}.
     *
     * @param exception the exception that was thrown.
     * @return a ResponseEntity with a CONFLICT status and the exception's message.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductAlreadyExistsException(ProductAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * A general exception handler for all types of exceptions.
     *
     * @param exception the exception that was thrown.
     * @return a ResponseEntity with an INTERNAL_SERVER_ERROR status and the exception's message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
