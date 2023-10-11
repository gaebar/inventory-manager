package com.inventory.inventorymanager.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.model.Notification;
import com.inventory.inventorymanager.repository.NotificationRepository;
import java.time.LocalDateTime;

// A service for sending out notifications when products need to be replenished or marked down.
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Sends an inventory notification.
     * @param product The product related to the notification.
     * @param message The notification message.
     */
    public void sendInventoryNotification(Product product, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setProduct(product);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}
