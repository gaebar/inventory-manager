package com.inventory.inventorymanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		System.out.println("Running Spring Boot Console Application");

	}
}
