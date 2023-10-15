package com.inventory.inventorymanager.cli;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.service.ProductService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;

public class ProductCLI {
    private final static Logger LOGGER = Logger.getLogger(ProductCLI.class.getName());


    public static void main(String[] args) {
        // Initialize ProductService
        ProductService productService = new ProductService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Product Management System!");
        System.out.println("This program allows you to manage your products' details, stock levels, expiry dates, and more.");
        System.out.println("Please follow the prompts to execute commands.");
        System.out.println("---------------------------------------------------------");

        while (true) {
            System.out.println("Select a command by entering the corresponding number:");
            System.out.println("1. Create Product");
            System.out.println("2. Display Product");
            System.out.println("3. Display All Products to Refill");
            System.out.println("4. Display Product to Refill");
            System.out.println("5. Display All Products Count");
            System.out.println("6. Display Product Count");
            System.out.println("7. Display Products by Expiry Date");
            System.out.println("8. Display All Products by Expiry Date");
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
                    try {
                        System.out.println("Enter ProductID: ");
                        Long productID = Long.parseLong(scanner.nextLine());

                        System.out.println("Enter ProductName: ");
                        String productName = scanner.nextLine();

                        System.out.println("Enter ExpiryDate (yyyy-MM-dd) or leave blank for default (3 months from today): ");
                        String expiryDateStr = scanner.nextLine();
                        LocalDate expiryDate = expiryDateStr.isEmpty() ? LocalDate.now().plusMonths(3) : LocalDate.parse(expiryDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        System.out.println("Enter TimeDurationForMarkDown or leave blank for default (6): ");
                        String timeDurationStr = scanner.nextLine();
                        int timeDurationForMarkDown = timeDurationStr.isEmpty() ? 6 : Integer.parseInt(timeDurationStr);

                        System.out.println("Enter MinThreshold: ");
                        Integer minThreshold = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter MaxThreshold: ");
                        Integer maxThreshold = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter CurrentStock: ");
                        Integer currentStock = Integer.parseInt(scanner.nextLine());

                        Product newProduct = new Product(productID, productName, expiryDate, timeDurationForMarkDown, minThreshold, maxThreshold, currentStock);
                        Product createdProduct = productService.createProduct(newProduct);

                        System.out.println("ProductName with the ProductID " + createdProduct.getProductID() + " created successfully.");
                        LOGGER.info("Product created successfully with ID: " + createdProduct.getProductID()); // Logging successful creation

                    } catch (ProductAlreadyExistsException e) {
                        LOGGER.warning("Product already exists: " + e.getMessage()); // Logging warning

                        System.out.println("ProductName should have a uniqueID, the ProductName already exists with the same uniqueID");
                    } catch (IllegalArgumentException e) {
                        System.out.println("productID and ProductName are required. Other arguments take default values.");
                    } catch (Exception e) {
                        LOGGER.severe("An error occurred: " + e.getMessage()); // Logging severe error

                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("You chose: Display Product");
                    try {
                        System.out.println("Enter ProductName (optional): ");
                        String productName = scanner.nextLine();

                        System.out.println("Enter ProductID (optional): ");
                        String productIdStr = scanner.nextLine();
                        Long productID = productIdStr.isEmpty() ? null : Long.parseLong(productIdStr);

                        Product foundProduct = productService.displayProduct(productName, productID);

                        if (foundProduct != null) {
                            System.out.println("Product Found:");
                            System.out.println("ProductID: " + foundProduct.getProductID());
                            System.out.println("ProductName: " + foundProduct.getProductName());
                            System.out.println("ExpiryDate: " + foundProduct.getExpiryDate());
                            System.out.println("TimeDurationForMarkDown: " + foundProduct.getTimeDurationForMarkDown());
                            System.out.println("MinThreshold: " + foundProduct.getMinThreshold());
                            System.out.println("MaxThreshold: " + foundProduct.getMaxThreshold());
                            System.out.println("CurrentStock: " + foundProduct.getCurrentStock());
                        } else {
                            System.out.println("ProductName/ProductID not found");
                        }

                    } catch (Exception e) {
                        LOGGER.severe("An error occurred: " + e.getMessage());
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("You chose: Display All Products to Refill");

                    break;
                case "4":
                    System.out.println("You chose: Display Product to Refill");

                    break;
                case "5":
                    System.out.println("You chose: Display All Products Count");

                    break;
                case "6":
                    System.out.println("You chose: Display Product Count");

                    break;
                case "7":
                    System.out.println("You chose: Display Products by Expiry Date");

                    break;
                case "8":
                    System.out.println("You chose: Display All Products by Expiry Date");

                    break;
                case "9":
                    System.out.println("You chose: Display Expired Products");

                    break;
                case "10":
                    System.out.println("You chose: Display Products in Markdown");

                    break;
                case "11":
                    System.out.println("You chose: Display Products for Future Markdown");

                    break;
                case "12":
                    System.out.println("You chose: Quit");
                    System.out.println("Thank you for using the Product Management System! Have a great day!");
                    LOGGER.info("Exiting application"); // Logging exit
                    scanner.close();
                    System.exit(0); // Exiting the application
                    break;

                default:
                    System.out.println("Invalid command. Please try again.");

            }
        }
    }
}
