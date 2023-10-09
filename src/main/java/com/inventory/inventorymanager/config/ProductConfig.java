package com.inventory.inventorymanager.config;

import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.respository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

// Configuration settings related to products, for setting up a database or default values.
@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository){
        return args -> {
            // Create sample products and save them to the database
            Product product1 = new Product(
                    "Baby wipes",
                    LocalDate.of(2024, 12, 31),
                    30,
                    10,
                    100,
                    50
            );
            repository.save(product1);

            Product product2 = new Product(
                    "Another Product",
                    LocalDate.of(2025, 1, 15),
                    15,
                    5,
                    50,
                    25
            );
            repository.save(product2);
        };
    }
}
