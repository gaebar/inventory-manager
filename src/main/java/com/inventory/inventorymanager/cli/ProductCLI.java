package com.inventory.inventorymanager.cli;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.service.ProductService;

import org.springframework.stereotype.Component;

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
        this.scanner = new Scanner(System.in); // Create the Scanner here
    }
    public void createProduct() {
        try (Scanner scanner = new Scanner(System.in)){
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
    }

    public void displayProduct() {
        try {
            System.out.println("Enter ProductName (optional): ");
            String productName = scanner.nextLine();

            System.out.println("Enter ProductID (optional): ");
            String productIdStr = scanner.nextLine();
            Long productID = productIdStr.isEmpty() ? null : Long.parseLong(productIdStr);

            if (productName.isEmpty() && productID == null) {
                // Display all products
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
        } catch (Exception e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public void quitApplication() {
        System.out.println("Thank you for using the Product Management System! Have a great day!");
        LOGGER.info("Exiting application");
        scanner.close();
        System.exit(0);
    }
}
