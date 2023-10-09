package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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
