package com.inventory.inventorymanager.cli;
import com.inventory.inventorymanager.service.ProductService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

@Component
@Profile("local")
public class CommandRouter implements CommandLineRunner{
    private final static Logger LOGGER = Logger.getLogger(CommandRouter.class.getName());

    private final ProductService productService;

  //  @Autowired
  //  private Environment env;

    public CommandRouter(ProductService productService) {
        this.productService = productService;
    }


   @Override
    public void run(String... args)  {
 //       String[] activeProfiles = env.getActiveProfiles();
 //       if (Arrays.asList(activeProfiles).contains("cli")) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Product Management System!");
            System.out.println("This program allows you to manage your products' details, stock levels, expiry dates, and more.");
            System.out.println("Please follow the prompts to execute commands.");
            System.out.println("---------------------------------------------------------");

            ProductCLI productCLI = new ProductCLI(productService);

            while (true) {
                System.out.println("Select a command by entering the corresponding number:");
                System.out.println("1. Create Product");
                System.out.println("2. Display Product");
                System.out.println("3. Display All Products to Refill");
                System.out.println("4. Display Product to Refill");
                System.out.println("5. Display All Products Count");
                System.out.println("6. Display Product Count");
                System.out.println("7. Display All Products by Expiry Date");
                System.out.println("8. Display Product by Expiry Date");
                System.out.println("9. Display Expired Products");
                System.out.println("10. Display Products in Markdown");
                System.out.println("11. Display Products for Future Markdown");
                System.out.println("12. Quit");
                System.out.print("Enter your choice: ");
                String command = scanner.nextLine();

                LOGGER.info("User entered command: " + command);

                switch (command) {
                    case "1":
                        System.out.println("You chose: Create Product");
                        productCLI.createProduct();
                        break;

                    case "2":
                        System.out.println("You chose: Display Product");
                        productCLI.displayProduct();
                        break;

                    case "3":
                        System.out.println("You chose: Display All Products to Refill");
                        productCLI.displayProductToRefill();
                        break;

                    case "4":
                        System.out.println("You chose: Display Product to Refill");
                        productCLI.displayProductToRefillUserChoice();
                        break;

                    case "5":
                        System.out.println("You chose: Display All Products Count");
                        productCLI.displayProductCount();
                        break;

                    case "6":
                        System.out.println("You chose: Display Product Count");
                        productCLI.displayProductCountUserChoice();
                        break;

                    case "7":
                        System.out.println("You chose: Display All Products by Expiry Date");
                        productCLI.displayProductExpiryDate();
                        break;

                    case "8":
                        System.out.println("You chose: Display Product by Expiry Date");
                        productCLI.displayProductExpiryDateUserChoice();
                        break;

                    case "9":
                        System.out.println("You chose: Display Expired Products");
                        productCLI.displayExpiredProducts();
                        break;

                    case "10":
                        System.out.println("You chose: Display Products in Markdown");
                        productCLI.displayProductsInMarkDown();
                        break;

                    case "11":
                        System.out.println("You chose: Display Products for Future Markdown");
                        productCLI.displayProductsForMarkDown();
                        break;

                    case "12":
                        System.out.println("You chose: Quit");
                        productCLI.quitApplication();
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            }
        }
    }
//}
