package com.inventory.inventorymanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryManagerApplicationTests {

	@Test
	void contextLoads() {
		// This test will automatically pass if the application context loads successfully.
	}

/* PERSONAL NOTE:

Previously, the contextLoads() method contained the line System.console().readLine();
This line would make the test pause and wait for user input from the console.
However, this behavior is not suitable for automated testing, as tests should run to completion without manual intervention.
This ensures that they can be reliably executed by automated build and test tools.
*/
}
