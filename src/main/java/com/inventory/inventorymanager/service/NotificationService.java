package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.model.Notification;
import com.inventory.inventorymanager.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Sends a notification with the given message and product.
     * The implementation details of how the notification is sent can be defined here.
     * This method also saves the notification to the database.
     *
     * @param product The related product.
     * @param message The notification message.
     */
    public void sendInventoryNotification(Product product, String message) {
        Notification notification = new Notification();
        notification.setProduct(product);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());

        // TODO (maybe): method to send the notification to the necessary parties (e.g., email, SMS).
        // sendNotificationToRecipient(notification);

        // Save the notification to the database
        notificationRepository.save(notification);
    }

}
