package com.inventory.inventorymanager.controller;

import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/check")
    public void checkAndUpdateInventory(@RequestBody Product product) {
        inventoryService.checkAndUpdateInventory(product);
    }

    @GetMapping("/checkAll")
    public void checkAllProductsInventory() {
        inventoryService.checkAllProductsInventory();
    }
}
