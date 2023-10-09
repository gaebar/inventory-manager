package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.model.Product;

import java.time.LocalDate;
import java.util.List;

public class ProductService {
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
