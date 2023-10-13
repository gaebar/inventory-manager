package com.inventory.inventorymanager.controller;

import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public void sendNotification(@RequestBody Product product, String message) {
        notificationService.sendInventoryNotification(product, message);
    }
}
