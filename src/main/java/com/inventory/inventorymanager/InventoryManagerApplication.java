package com.inventory.inventorymanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Inventory Manager application.
 * This is a Spring Boot application designed to run from the command line.
 * It implements CommandLineRunner to allow for execution of application logic
 * once the application has fully started.
 *
 * @author Gaetano  Barreca
 * @version 1.0
 */
@SpringBootApplication
public class InventoryManagerApplication implements CommandLineRunner {

	/**
	 * Main method which serves as the entry point for the Spring Boot application.
	 *
	 * @param args Command line arguments passed to the application.
	 */

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerApplication.class, args);
	}

	/**
	 * This method is executed after the application context is loaded.
	 * It can be used to run application logic or to process command line arguments.
	 *
	 * @param args Command line arguments passed to the application.
	 */
	@Override
	public void run(String... args)  {
		System.out.println("Running Spring Boot Console Application");

	}
}
