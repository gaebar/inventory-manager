package com.inventory.inventorymanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Entity annotation specifies that the class is an entity and is mapped to a database table
@Entity
// Table annotation provides the table details
@Table(name = "notifications")
public class Notification {

    // ID annotation specifies the primary key of the entity
    // GeneratedValue sets the generation strategy for the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ManyToOne specifies a many-to-one relationship between Notification and Product entities
    // JoinColumn specifies the foreign key column
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Field to store the message of the notification
    @Column(name = "message")
    private String message;

    // Field to store the timestamp when the notification was created
    @Column(name = "timestamp", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;

    // Default constructor required by JPA
    public Notification() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // ToString method for debugging and logging
    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", product=" + product +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
