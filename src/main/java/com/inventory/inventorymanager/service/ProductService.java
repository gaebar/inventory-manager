package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing products.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Value("${default.expiry.duration}")
    private int defaultExpiryDuration;

    @Value("${default.markdown.duration}")
    private int defaultMarkdownDuration;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);


    /**
     * Fetches all products from the database.
     *
     * @return List of all products.
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Fetches a product by its ID.
     *
     * @param id The ID of the product.
     * @return The product.
     * @throws ProductNotFoundException if the product is not found.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
    }


    /**
     * Validates the product entity before attempting to save it.
     * This internal method is invoked by the createProduct() method.
     *
     * @param product The product entity to validate.
     */
    private void validateProduct(Product product) {
        if (product.getProductID() == null || product.getProductName() == null || product.getProductName().isEmpty()) {
            LOGGER.error("Missing required fields");
            throw new IllegalArgumentException("productID and ProductName are required. Other arguments take default values.");
        }
        if (productRepository.existsByProductID(product.getProductID())) {
            LOGGER.error("Product with same ID already exists");
            throw new ProductAlreadyExistsException("ProductName should have a uniqueID, the ProductName already exists with the same uniqueID: " + product.getProductID());
        }
    }

    /**
     * Creates a product and stores it in the database.
     * Sets default values for ExpiryDate and TimeDurationForMarkDown if not provided.
     *
     * @param product The product entity to be created.
     * @return The created product.
     * @throws DataIntegrityViolationException if a product with the given ProductID already exists.
     */
    @Transactional
    public Product createProduct(Product product) {
        // Removed validateProduct() call, relying on JPA for uniqueness checks
        if (product.getExpiryDate() == null) {
            product.setExpiryDate(LocalDate.now().plusMonths(defaultExpiryDuration));
        }

        if (product.getTimeDurationForMarkDown() == null) {
            long daysToMarkDown = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), product.getExpiryDate());
            product.setTimeDurationForMarkDown((int) daysToMarkDown - defaultMarkdownDuration);
        }

        Product savedProduct = null;
        try {
            savedProduct = productRepository.save(product);
            LOGGER.info("ProductName with the ProductID {} created successfully.", savedProduct.getProductID());
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Product with same ID already exists", e);
            throw new ProductAlreadyExistsException("ProductName should have a uniqueID, the ProductName already exists with the same uniqueID: " + product.getProductID());
        }

        return savedProduct;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     * @throws ProductNotFoundException if the product is not found.
     */
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

    /**
     * Updates a product by its ID.
     *
     * @param id The ID of the product to be updated.
     * @param newProduct The new product details.
     * @return The updated product.
     * @throws ProductNotFoundException if the product is not found.
     */
    @Transactional
    public Product updateProduct(Long id, Product newProduct) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        newProduct.setProductID(id);
        return productRepository.save(newProduct);
    }

    /**
     * Retrieves a list of products with stock below a given threshold.
     *
     * @param threshold Stock threshold.
     * @return List of products with stock below the threshold.
     */
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findByCurrentStockLessThan(threshold);
    }

    /**
     * Retrieves a list of products expiring before a specified date.
     *
     * @param expiryDate The date to check against.
     * @return List of products expiring before the specified date.
     */
    public List<Product> getExpiringProducts(LocalDate expiryDate) {
        return productRepository.findByExpiryDateBefore(expiryDate);
    }

    public Product displayProduct(String productName, Long productId) {
        if (productName != null) {
            return productRepository.findByProductName(productName)
                    .orElseThrow(() -> new ProductNotFoundException("Product with name " + productName + " not found."));
        } else if (productId != null) {
            return getProductById(productId); // Reuse the existing method.
        } else {
            // Display all products
            List<Product> products = getProducts(); // Reuse the existing method.
            // Logic to display these products in the console/GUI goes here.
            return null; // Adjust return type based on your requirements.
        }
    }

    /**
     * Retrieves a list of products that need refilling.
     *
     * @param productId The ID of the product.
     * @return List of products that need refilling.
     */
    public List<Product> displayProductToRefill(Long productId) {
        return productRepository.findByCurrentStockLessThanAndProductID(10, productId);
    }

    /**
     * Retrieves the current stock of a product.
     *
     * @param productId The ID of the product.
     * @return Current stock of the product.
     */
    public int displayProductCount(Long productId) {
        Product product = getProductById(productId);
        return product.getCurrentStock();
    }


    public LocalDate displayProductsExpiryDate(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("ProductID is mandatory for displayProductsExpiryDate.");
        }
        Product product = getProductById(productId);
        return product.getExpiryDate();
    }

    public List<Product> displayExpiredProducts() {
        LocalDate today = LocalDate.now();
        return productRepository.findByExpiryDateBefore(today);
    }

    /**
     * Retrieves a list of products that are currently in markdown.
     *
     * @return List of products in markdown.
     */
    public List<Product> displayProductsInMarkDown() {
        LocalDate today = LocalDate.now();
        return productRepository.findInMarkDown(today);
    }

    /**
     * Retrieves a list of products that need to be marked down within a week.
     *
     * @return List of products for markdown.
     */
    public List<Product> displayProductsForMarkDown() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekFromNow = today.plusDays(7);
        return productRepository.findForMarkDown(today, oneWeekFromNow);
    }
}
