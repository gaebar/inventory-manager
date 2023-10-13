package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final ProductService productService;
    private final NotificationService notificationService;

    private static final String LOW_INVENTORY_MESSAGE = "Inventory low: Replenish product %s";
    private static final String HIGH_INVENTORY_MESSAGE = "Inventory high: No replenishment needed for %s";

    @Autowired
    public InventoryService(ProductService productService, NotificationService notificationService) {
        this.productService = productService;
        this.notificationService = notificationService;
    }

    public void checkAndUpdateInventory(Product product) throws ProductNotFoundException {
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        int currentStock = product.getCurrentStock();
        String message = null;
        if (currentStock <= product.getMinThreshold()) {
            message = String.format(LOW_INVENTORY_MESSAGE, product.getProductName());
        } else if (currentStock >= product.getMaxThreshold()) {
            message = String.format(HIGH_INVENTORY_MESSAGE, product.getProductName());
        }

        if (message != null) {
            notificationService.sendInventoryNotification(product, message);
        }
    }

    public void checkAllProductsInventory() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            // Handle this scenario as you see fit; you could log it, throw an exception, etc.
        }
        for (Product product : products) {
            try {
                checkAndUpdateInventory(product);
            } catch (ProductNotFoundException e) {
                // Log the exception or handle it as appropriate.
            }
        }
    }
}

