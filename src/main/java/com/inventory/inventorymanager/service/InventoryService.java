package com.inventory.inventorymanager.service;

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

    public void checkAndUpdateInventory(Product product) {
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
        for (Product product : products) {
            checkAndUpdateInventory(product);
        }
    }
}

