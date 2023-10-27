package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing products.
 */
@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    @Value("${default.expiry.duration}")
    private int defaultExpiryDuration;

    @Value("${default.markdown.duration}")
    private int defaultMarkdownDuration;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
    }

    public int getQuantityToRefill(Product product) {
        return product.getMaxThreshold() - product.getCurrentStock();
    }


    /**
     * Creates a product and stores it in the database.
     * Sets default values for ExpiryDate and TimeDurationForMarkDown if not provided.
     *
     * @return The created product.
     * @throws DataIntegrityViolationException if a product with the given ProductID already exists.
     */


    @Transactional
    public Product createProduct(long productID, String productName, LocalDate expiryDate, Integer timeDurationForMarkDown, Integer minThreshold, Integer maxThreshold, Integer currentStock) {

        Product newProduct = new Product(productID, productName, expiryDate, timeDurationForMarkDown, minThreshold, maxThreshold, currentStock);

        if (expiryDate == null) {
            newProduct.setExpiryDate(LocalDate.now().plusMonths(defaultExpiryDuration));
        }

        if (newProduct.getTimeDurationForMarkDown() == null) {
            long daysToMarkDown = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), newProduct.getExpiryDate());
            newProduct.setTimeDurationForMarkDown((int) daysToMarkDown - defaultMarkdownDuration);
        }

        Product savedProduct = null;
        try {
            savedProduct = productRepository.save(newProduct);
            LOGGER.info("ProductName with the ProductID {} created successfully.", savedProduct.getProductID());
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Product with same ID already exists", e);
            throw new ProductAlreadyExistsException("ProductName should have a uniqueID, the ProductName already exists with the same uniqueID: " + newProduct.getProductID());
        }

        return savedProduct;
    }

    @Transactional
    public boolean existProductID(Long id) {
        return productRepository.existsById(id);
    }

    @Transactional
    public boolean existProductName(String productName) {
        return productRepository.existsByProductName(productName);
    }


    /**
     * Retrieves a list of products expiring before a specified date.
     *
     * @return List of products expiring before the specified date.
     */

    public Product displayProduct(String productName, Long productId) {
        if (productName != null) {
            return productRepository.findByProductName(productName).orElseThrow(() -> new ProductNotFoundException("Product with name " + productName + " not found."));
        } else if (productId != null) {
            return getProductById(productId); // Reuse the existing method.
        } else {
            // Display all products
            List<Product> products = getProducts(); // Reuse the existing method.
            // Logic to display these products in the console/GUI goes here.
            return null; // Adjust return type based on your requirements.
        }
    }

    public List<Product> displayExpiredProducts() {
        LocalDate today = LocalDate.now();
        return productRepository.findByExpiryDateBefore(today);
    }

    /**
     * Calculates the date by subtracting the time duration for markdown from the expiry date of a product.
     *
     * @param product The product entity.
     * @return The calculated date.
     */
    public LocalDate calculateMarkdownThresholdDate(Product product) {
        if (product == null || product.getExpiryDate() == null || product.getTimeDurationForMarkDown() == null) {
            throw new IllegalArgumentException("Product, expiry date, and time duration for markdown must not be null");
        }
        return product.getExpiryDate().minusDays(product.getTimeDurationForMarkDown());
    }

    /**
     * Retrieves a list of products that are past their Markdown Date.
     *
     * @return List of products past Markdown Date.
     */
    public List<Product> displayProductsInMarkDown() {
        LocalDate today = LocalDate.now();

        List<Product> productsPastMarkdown = productRepository.findPastMarkdownDate(today);
        if (productsPastMarkdown.isEmpty()) {
            System.out.println("No products past their Markdown Date found.");
        }
        return productsPastMarkdown;
    }

    /**
     * Retrieves a list of products that will be marked down within a week.
     *
     * @return List of products for future markdown.
     */
    public List<Product> displayProductsForMarkDown() {
        LocalDate today = LocalDate.now();
        List<Product> productsForMarkDownWithinWeek = productRepository.findForMarkDownWithinWeek(today);
        if (productsForMarkDownWithinWeek.isEmpty()) {
            System.out.println("No products to be marked down within a week found.");
        }
        return productsForMarkDownWithinWeek;
    }
}
