package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Notification;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service layer for handling product notifications.
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    /**
     * Sends a notification related to a given product.
     *
     * @param product The related product.
     * @param message The message to include in the notification.
     * @throws ProductNotFoundException if the product is null.
     */
    public void sendInventoryNotification(Product product, String message) throws ProductNotFoundException {
        if (product == null) {
            LOGGER.error("Attempted to send a notification for a null product.");
            throw new ProductNotFoundException("Product not found for notification");
        }

        Notification notification = new Notification();
        notification.setProduct(product);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());

        notificationRepository.save(notification);
        LOGGER.info("Notification sent for product with ID {}", product.getProductID());
    }

    /**
     * Sends notifications for products with stock below a given threshold.
     *
     * @param threshold Stock threshold.
     */
    public void sendLowStockNotifications(int threshold) {
        List<Product> lowStockProducts = productService.getLowStockProducts(threshold);

        for (Product product : lowStockProducts) {
            String message = "Product with ID " + product.getProductID() + " has low stock!";
            sendInventoryNotification(product, message);
        }
    }
}