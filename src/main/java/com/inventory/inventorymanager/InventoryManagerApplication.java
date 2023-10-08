package com.inventory.inventorymanager;

import com.inventory.inventorymanager.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class InventoryManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerApplication.class, args);
	}

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
