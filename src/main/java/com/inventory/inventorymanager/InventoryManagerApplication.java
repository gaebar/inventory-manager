package com.inventory.inventorymanager;

// import com.inventory.inventorymanager.cli.CommandRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerApplication.class, args);

		// TODO: Work on integrating the CLI (Command Line Interface)
		// PERSONAL NOTE: even with the CLI, I can still access the HTTP endpoints.
		// The CLI is just another way to interact with the application locally.

		// Check for command-line arguments
//		if(args.length > 0) {
//			CommandRouter router = new CommandRouter();
//			router.route(args[0]);
//		}
	}
}