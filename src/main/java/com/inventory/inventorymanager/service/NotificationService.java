package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.model.Notification;
import com.inventory.inventorymanager.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service layer for handling product notifications.
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Sends a notification related to a given product.
     *
     * @param product The related product.
     * @param message The message to include in the notification.
     * @throws ProductNotFoundException if the product is null.
     */
    public void sendInventoryNotification(Product product, String message) throws ProductNotFoundException {
        if (product == null) {
            throw new ProductNotFoundException("Product not found for notification");
        }

        Notification notification = new Notification();
        notification.setProduct(product);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}