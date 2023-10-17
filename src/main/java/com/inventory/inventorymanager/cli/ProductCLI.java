package com.inventory.inventorymanager.cli;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.service.ProductService;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Component
public class ProductCLI {
    private final static Logger LOGGER = Logger.getLogger(ProductCLI.class.getName());
    private final ProductService productService;
    private final Scanner scanner;

    public ProductCLI(ProductService productService) {
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }
    public void createProduct() {
        long productID;
        String productName;
        LocalDate expiryDate;
        int timeDurationForMarkDown;
        int minThreshold;
        int maxThreshold;
        int currentStock;


        while (true) {
            try {
                System.out.println("Enter ProductName: ");
                productName = scanner.nextLine();
                if (productName == null || productName.trim().isEmpty()) {
                    System.out.println("ProductName and ProductID are required.");
                    System.out.println("Other arguments take default values.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ProductName format. Please enter a valid character.");
            }
        }

        while (true) {
            try {
                System.out.println("Enter ProductID: ");
                productID = Long.parseLong(scanner.nextLine());

                if(productService.existProductID(productID)){
                    System.out.println("ProductName should have a uniqueID, the ProductName already exists with the same uniqueID");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ProductID format. Please enter a valid number.");
            }
        }

        while (true) {
            try {
                System.out.println("Enter ExpiryDate (yyyy-MM-dd) or leave blank for default (3 months from today): ");
                String expiryDateStr = scanner.nextLine();
                expiryDate = expiryDateStr.isEmpty() ? LocalDate.now().plusMonths(3) : LocalDate.parse(expiryDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            }
        }

        while (true) {
            try {
                System.out.println("Enter TimeDurationForMarkDown or leave blank for default (6): ");
                String timeDurationStr = scanner.nextLine();
                timeDurationForMarkDown = timeDurationStr.isEmpty() ? 6 : Integer.parseInt(timeDurationStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number.");
            }
        }

        while (true) {
            try {
                System.out.println("Enter MinThreshold: ");
                minThreshold = Integer.parseInt(scanner.nextLine());
                break;  // Exit the loop when valid input is given
            } catch (NumberFormatException e) {
                System.out.println("Invalid MinThreshold format. Please enter a valid number.");
            }
        }

        while (true) {
            try {
                System.out.println("Enter MaxThreshold: ");
                maxThreshold = Integer.parseInt(scanner.nextLine());
                break;  // Exit the loop when valid input is given
            } catch (NumberFormatException e) {
                System.out.println("Invalid MaxThreshold format. Please enter a valid number.");
            }
        }

        while (true) {
            try {
                System.out.println("Enter CurrentStock: ");
                currentStock = Integer.parseInt(scanner.nextLine());
                break;  // Exit the loop when valid input is given
            } catch (NumberFormatException e) {
                System.out.println("Invalid CurrentStock format. Please enter a valid number.");
            }
        }

        try {
            this.createProduct(productID, productName, expiryDate, timeDurationForMarkDown, minThreshold, maxThreshold, currentStock);

        } catch (ProductAlreadyExistsException e) {
            LOGGER.warning("Product already exists: " + e.getMessage());
            System.out.println("ProductName should have a unique ID. The product already exists with the same unique ID.");

        } catch (Exception e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }

        promptForAnotherOperationOrExit();
    }

    public void createProduct(long productID, String productName, LocalDate expiryDate, Integer timeDurationForMarkDown,  Integer minThreshold, Integer maxThreshold, Integer currentStock){
        Product createdProduct = productService.createProduct(productID, productName, expiryDate, timeDurationForMarkDown, minThreshold, maxThreshold, currentStock);
        System.out.println("ProductName with the ProductID " + createdProduct.getProductID() + " created successfully.");
        LOGGER.info("Product created successfully with ID: " + createdProduct.getProductID());
    }


    public void displayProduct() {
        do {
            try {
                System.out.println("Enter ProductName (optional): ");
                String productName = scanner.nextLine();

                System.out.println("Enter ProductID (optional): ");
                String productIdStr = scanner.nextLine();
                Long productID = productIdStr.isEmpty() ? null : Long.parseLong(productIdStr);

                if (productName.isEmpty() && productID == null) {
                    // Display all products
                    System.out.println("Leave both fields blank to display all products");  // Added this line
                    List<Product> allProducts = productService.getProducts();
                    if (allProducts.isEmpty()) {
                        LOGGER.warning("No products found.");
                        System.out.println("No products found.");
                    } else {
                        LOGGER.info("All products displayed successfully");
                        for (Product p : allProducts) {
                            System.out.println("ProductID: " + p.getProductID());
                            System.out.println("ProductName: " + p.getProductName());
                            System.out.println("ExpiryDate: " + p.getExpiryDate());
                            System.out.println("TimeDurationForMarkDown: " + p.getTimeDurationForMarkDown());
                            System.out.println("MinThreshold: " + p.getMinThreshold());
                            System.out.println("MaxThreshold: " + p.getMaxThreshold());
                            System.out.println("CurrentStock: " + p.getCurrentStock());
                            System.out.println("-------------------------------------------------");
                        }
                    }
                } else {
                    Product foundProduct = productService.displayProduct(productName, productID);
                    if (foundProduct != null) {
                        LOGGER.info("Product with ProductID: " + foundProduct.getProductID() + " displayed successfully.");
                        System.out.println("Product Found:");
                        System.out.println("ProductID: " + foundProduct.getProductID());
                        System.out.println("ProductName: " + foundProduct.getProductName());
                        System.out.println("ExpiryDate: " + foundProduct.getExpiryDate());
                        System.out.println("TimeDurationForMarkDown: " + foundProduct.getTimeDurationForMarkDown());
                        System.out.println("MinThreshold: " + foundProduct.getMinThreshold());
                        System.out.println("MaxThreshold: " + foundProduct.getMaxThreshold());
                        System.out.println("CurrentStock: " + foundProduct.getCurrentStock());
                    } else {
                        LOGGER.warning("Product with given ProductName/ProductID not found.");
                        System.out.println("ProductName/ProductID not found");
                    }
                }
                break;

            } catch (InputMismatchException | NumberFormatException e) {
                LOGGER.warning("Invalid input format: " + e.getMessage());
                System.out.println("Please enter the correct format for each field.");

            } catch (Exception e) {
                LOGGER.severe("An error occurred: " + e.getMessage());
                System.out.println("An error occurred: " + e.getMessage());
            }

        } while (!promptForAnotherOperationOrExit());
    }


    public void quitApplication() {
        System.out.println("Thank you for using the Product Management System! Have a great day!");
        LOGGER.info("Exiting application");
        scanner.close();
        System.exit(0);
    }


    private boolean promptForAnotherOperationOrExit() {
        while (true) {
            System.out.println("Do you want to perform another operation? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if ("y".equals(choice)) {
                return true;  // Continue with another operation
            } else if ("n".equals(choice)) {
                quitApplication();  // If user chooses not to perform another operation, exit the program
                return false;  // This line won't be reached, but it's good to have a definitive return value
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
            }
        }
    }
}
