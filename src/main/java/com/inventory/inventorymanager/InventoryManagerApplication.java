package com.inventory.inventorymanager;

import com.inventory.inventorymanager.cli.CommandRouter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagerApplication {

	@Autowired
	private CommandRouter commandRouter;

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerApplication.class, args);
	}

	@PostConstruct
	public void runApplication() {
		commandRouter.start();
	}
}
