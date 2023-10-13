package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.model.Notification;
import com.inventory.inventorymanager.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * Constructor for NotificationService. Initializes the repository for performing CRUD operations on Notification entities.
     *
     * @param notificationRepository Repository responsible for CRUD operations on Notification entities.
     */
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendInventoryNotification(Product product, String message) throws ProductNotFoundException {
        if (product == null) {
            throw new ProductNotFoundException("Product not found for notification");
        }
        Notification notification = new Notification();
        notification.setProduct(product);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());

        // Save the notification to the database
        notificationRepository.save(notification);

        // TODO: Add method to send the notification to necessary parties (e.g., email, SMS).
    }
}