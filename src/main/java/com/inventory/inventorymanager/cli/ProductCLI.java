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
    private static final Logger LOGGER = Logger.getLogger(ProductCLI.class.getName());

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
                if(productService.existProductName(productName)){
                    System.out.println("ProductName should be unique. A product with the name '" + productName + "' already exists.");
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
                    System.out.println("ProductName should have a uniqueID, the ProductName '" + productName + "' already exists with the same uniqueID");
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
            createProduct(productID, productName, expiryDate, timeDurationForMarkDown, minThreshold, maxThreshold, currentStock);

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
        System.out.println("ProductName " + createdProduct.getProductName() + " with the ProductID " + createdProduct.getProductID() + " created successfully.");
        LOGGER.info("Product created successfully with ID: " + createdProduct.getProductID());
    }


    public void displayProduct() {
        System.out.println("To display all products, leave both the ProductName and ProductID fields blank.");
        System.out.println("Enter ProductName (optional): ");
        String productName = scanner.nextLine();

        System.out.println("Enter ProductID (optional): ");
        String productIdStr = scanner.nextLine();
        Long productID = productIdStr.isEmpty() ? null : Long.parseLong(productIdStr);

        displayProduct(productName, productID); // Calls the overloaded method
    }

    public void displayProduct(String productName, Long productID) {
        try {
            // If both fields are empty, display all products.
            if ((productName == null || productName.isEmpty()) && productID == null) {
                // Display all products
                List<Product> allProducts = productService.getProducts();
                if (allProducts.isEmpty()) {
                    LOGGER.warning("No products found.");
                    System.out.println("No products found.");
                } else {
                    LOGGER.info("All products displayed successfully");
                    for (Product p : allProducts) {
                        displayProductDetails(p);
                    }
                    System.out.println("All products displayed successfully");
                }
            } else {
                Product foundProduct = productService.displayProduct(productName, productID);

                if (foundProduct != null) {
                    LOGGER.info("Product with ProductID: " + foundProduct.getProductID() + " displayed successfully.");
                    System.out.println("Product Found:");
                    displayProductDetails(foundProduct);
                } else {
                    LOGGER.warning("Product with given ProductName/ProductID not found.");
                    System.out.println("ProductName/ProductID not found");
                }
            }
        } catch (InputMismatchException | NumberFormatException e) {
            LOGGER.warning("Invalid input format: " + e.getMessage());
            System.out.println("Please enter the correct format for each field.");
        } catch (Exception e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }
        promptForAnotherOperationOrExit();
    }

    public void displayAllProductsToRefill() {
        try {
            // Fetch all products from the service
            List<Product> allProducts = productService.getProducts();

            // Use a flag to check if there are any products to refill
            boolean productsToRefillExist = false;

            // Loop through each product and check if it needs to be refilled
            for (Product p : allProducts) {
                // Check if current stock is less than or equal to the minThreshold
                if (p.getCurrentStock() <= p.getMinThreshold()) {
                    productsToRefillExist = true;

                    // Calculate the quantity to be replenished
                    int quantityToReplenish = p.getMaxThreshold() - p.getCurrentStock();

                    System.out.println("-------------------------------------------------");
                    System.out.println("ProductID: " + p.getProductID());
                    System.out.println("ProductName: " + p.getProductName());
                    System.out.println("CurrentStock: " + p.getCurrentStock());
                    System.out.println("MinThreshold: " + p.getMinThreshold());
                    System.out.println("MaxThreshold: " + p.getMaxThreshold());
                    System.out.println("Quantity to Replenish: " + quantityToReplenish);
                    System.out.println("-------------------------------------------------");
                }
            }

            // If no products need to be refilled, display a relevant message
            if (!productsToRefillExist) {
                LOGGER.info("All products are sufficiently stocked, no products need replenishment.");
                System.out.println("All products are sufficiently stocked, no products need replenishment.");
            }

        } catch (Exception e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }
        promptForAnotherOperationOrExit();
    }

    public void displayProductToRefill() {
        try {
            System.out.println("Enter ProductID: ");
            long productID = Long.parseLong(scanner.nextLine());

            // Fetch the product with the given ID
            Product product = productService.getProductById(productID);


            if (product == null) {
                LOGGER.warning("Product with given ProductID not found.");
                System.out.println("Product with given ProductID not found.");
                return;
            }

            // Check if current stock is less than or equal to the minThreshold
            if (product.getCurrentStock() <= product.getMinThreshold()) {
                // Calculate the quantity to be replenished
                int quantityToReplenish = product.getMaxThreshold() - product.getCurrentStock();

                System.out.println("-------------------------------------------------");
                System.out.println("ProductID: " + product.getProductID());
                System.out.println("ProductName: " + product.getProductName());
                System.out.println("CurrentStock: " + product.getCurrentStock());
                System.out.println("MinThreshold: " + product.getMinThreshold());
                System.out.println("MaxThreshold: " + product.getMaxThreshold());
                System.out.println("Quantity to Replenish: " + quantityToReplenish);
                System.out.println("-------------------------------------------------");
            } else {
                LOGGER.info("Product is sufficiently stocked and does not need replenishment.");
                System.out.println("Product is sufficiently stocked and does not need replenishment.");
            }
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid ProductID format. Please enter a valid number.");
            System.out.println("Invalid ProductID format. Please enter a valid number.");
        } catch (Exception e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }
        promptForAnotherOperationOrExit();
    }

    public void displayAllProductsCount() {
        try {
            // Fetch all products from the service
            List<Product> allProducts = productService.getProducts();

            // Check if there are any products
            if (allProducts.isEmpty()) {
                LOGGER.warning("No products found on the shelf.");
                System.out.println("No products found on the shelf.");
            } else {
                int totalProducts = allProducts.size();
                LOGGER.info("Total number of products on the shelf: " + totalProducts);
                System.out.println("Total number of products on the shelf: " + totalProducts);
            }
        } catch (Exception e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }
        promptForAnotherOperationOrExit();
    }

    /**
     * Display the count of a product based on its ProductID.
     */
    public void displayProductCount() {
        try {
            System.out.println("Enter ProductID to get its count on the shelf:  ");  // Prompt the user to enter ProductID
            long productID = Long.parseLong(scanner.nextLine());

            // Fetch the product with the given ID
            Product product = productService.getProductById(productID);

            // If the product doesn't exist, display a relevant message and return
            if (product == null) {
                LOGGER.warning("Product with given ProductID not found.");
                System.out.println("Product with given ProductID not found.");
                return;
            }

            // Display the current stock of the product
            System.out.println("-------------------------------------------------");
            System.out.println("ProductID: " + product.getProductID());
            System.out.println("ProductName: " + product.getProductName());
            System.out.println("CurrentStock: " + product.getCurrentStock());
            System.out.println("-------------------------------------------------");

        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid ProductID format. Please enter a valid number.");
            System.out.println("Invalid ProductID format. Please enter a valid number.");
        } catch (Exception e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }
        promptForAnotherOperationOrExit();
    }

    public void displayAllProductsExpiryDate(){}
    public void displayProductExpiryDate(){}

    public void displayExpiredProducts(){}

    public void displayProductsInMarkDown(){}

    public void displayProductsForMarkDown(){}


    public void quitApplication() {
        System.out.println("Thank you for using the Product Management System! Have a great day!");
        LOGGER.info("Exiting application");
        scanner.close();
        System.exit(0);
    }


    private void promptForAnotherOperationOrExit() {
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }

    private void displayProductDetails(Product p) {
        System.out.println("-------------------------------------------------");
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
