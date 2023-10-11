package com.inventory.inventorymanager.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.inventory.inventorymanager.service.ProductService;

@Component
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private ProductService productService;

    @Scheduled(fixedRate = 60000)  // Runs every 60 seconds
    public void checkInventory() {
        productService.checkAllProductsInventory();
    }
}