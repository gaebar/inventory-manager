package com.inventory.inventorymanager.repository;

import com.inventory.inventorymanager.model.Notification;
import com.inventory.inventorymanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// The repository interface for accessing Notification entities.
// It extends JpaRepository to inherit built-in CRUD operations.
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Custom queries can be added here.

    // A sample custom query to find all Notifications related to a specific Product.
    // This method will be auto-implemented by Spring Data JPA.
    List<Notification> findByProduct(Product product);
    /**
     * Retrieves all notifications generated after a particular timestamp.
     * @param timestamp The timestamp to check against.
     * @return List of notifications generated after the given timestamp.
     */
    List<Notification> findByTimestampAfter(LocalDateTime timestamp);

    /**
     * Retrieves all notifications that contain a specific substring in their message.
     * @param message The substring to search for in the notification messages.
     * @return List of notifications containing the given substring in their messages.
     */
    List<Notification> findByMessageContaining(String message);
}
