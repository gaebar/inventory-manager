package com.inventory.inventorymanager.controller;

import com.inventory.inventorymanager.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                new Product(
                        "546789",
                        "Baby wipes",
                        LocalDate.of(2024, 12, 31),
                        30,
                        10,
                        100,
                        50
                )
        );
    }
}
