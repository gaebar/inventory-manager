package com.inventory.inventorymanager.model;

import java.time.LocalDate;
public class Product {
    private String productID;
    private String productName;
    private LocalDate expiryDate;
    private int timeDurationForMarkDown;
    private int minThreshold;
    private int maxThreshold;
    private int currentStock;

    // I am using three constructors
    // 1. No arg constructor
    public Product() {
    }

    // 2. Full Constructor
    public Product(String productID, String productName, LocalDate expiryDate, int timeDurationForMarkDown, int minThreshold, int maxThreshold, int currentStock) {
        this.productID = productID;
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.timeDurationForMarkDown = timeDurationForMarkDown;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.currentStock = currentStock;
    }

    // 3. Constructor without ID as the database will generate one for us
    public Product(String productName, LocalDate expiryDate, int timeDurationForMarkDown, int minThreshold, int maxThreshold, int currentStock) {
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.timeDurationForMarkDown = timeDurationForMarkDown;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.currentStock = currentStock;
    }

    // Getters and Setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getTimeDurationForMarkDown() {
        return timeDurationForMarkDown;
    }

    public void setTimeDurationForMarkDown(int timeDurationForMarkDown) {
        this.timeDurationForMarkDown = timeDurationForMarkDown;
    }

    public int getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(int minThreshold) {
        this.minThreshold = minThreshold;
    }

    public int getMaxThreshold() {
        return maxThreshold;
    }

    public void setMaxThreshold(int maxThreshold) {
        this.maxThreshold = maxThreshold;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", expiryDate=" + expiryDate +
                ", timeDurationForMarkDown=" + timeDurationForMarkDown +
                ", minThreshold=" + minThreshold +
                ", maxThreshold=" + maxThreshold +
                ", currentStock=" + currentStock +
                '}';
    }
}

// PRODUCT CLASS NOTES:
// Chose java.time.LocalDate over java.util.Date for these key reasons:
// 1. Simplicity: Only a date is needed, no time or timezone.
// 2. Immutability: Thread-safe.
// 3. Modern API: More robust and easier to use.
// Ideal for storing just the expiry date of products.

// Chose String for productId over Integer for the following reasons:
// 1. Flexibility: String can accommodate various ID formats (e.g., alphanumeric, special characters).
// 2. Scalability: No risk of running out of numbers, as might happen with integers.
// 3. Compatibility: Easier integration with external systems that use non-numeric IDs.
// 4. Readability: Possibility to embed semantic meaning within the ID (e.g., "PROD1234").
