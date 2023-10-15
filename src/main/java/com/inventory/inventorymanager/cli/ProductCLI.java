package com.inventory.inventorymanager.cli;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.service.ProductService;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class ProductCLI {

    public static void main(String[] args) {
        // Initialize ProductService
        ProductService productService = new ProductService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 'create' to create a new product or 'quit' to exit: ");
            String command = scanner.nextLine();

            if ("quit".equals(command)) {
                break;
            } else if ("create".equals(command)) {
                try {
                    System.out.println("Enter ProductID: ");
                    Long productID = Long.parseLong(scanner.nextLine());

                    System.out.println("Enter ProductName: ");
                    String productName = scanner.nextLine();

                    System.out.println("Enter ExpiryDate (yyyy-MM-dd) or leave blank for default: ");
                    String expiryDateStr = scanner.nextLine();
                    LocalDate expiryDate = expiryDateStr.isEmpty() ? LocalDate.now().plusMonths(3) : LocalDate.parse(expiryDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    System.out.println("Enter TimeDurationForMarkDown or leave blank for default: ");
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
                } catch (ProductAlreadyExistsException e) {
                    System.out.println("ProductName should have a uniqueID, the ProductName already exists with the same uniqueID");
                } catch (IllegalArgumentException e) {
                    System.out.println("productID and ProductName are required. Other arguments take default values.");
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }
}
