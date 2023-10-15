//package com.inventory.inventorymanager.controller;
//
//import com.inventory.inventorymanager.model.Product;
//import com.inventory.inventorymanager.service.ProductService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.time.LocalDate;
//
//@RestController
//@RequestMapping(path = "api/v1/products")
//public class ProductController {
//
//    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping
//    public ResponseEntity<List<Product>> getProducts() {
//        logger.info("Fetching all products");
//        List<Product> products = productService.getProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
//        logger.info("Fetching product with id: {}", id);
//        Product product = productService.getProductById(id);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
//        logger.info("Creating new product");
//        Product createdProduct = productService.createProduct(product);
//        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        logger.info("Deleting product with id: {}", id);
//        productService.deleteProduct(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
//        logger.info("Updating product with id: {}", id);
//        Product updatedProduct = productService.updateProduct(id, product);
//        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//    }
//
//    @GetMapping("/expiring")
//    public ResponseEntity<List<Product>> getProductsExpiringBefore(@RequestParam LocalDate date) {
//        logger.info("Fetching products expiring before: {}", date);
//        List<Product> products = productService.getProductsExpiringBefore(date);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//    @GetMapping("/refill")
//    public ResponseEntity<List<Product>> getProductsToRefill(@RequestParam Integer threshold) {
//        logger.info("Fetching products with stock less than: {}", threshold);
//        List<Product> products = productService.getProductsToRefill(threshold);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//
//    @GetMapping("/markdown")
//    public ResponseEntity<List<Product>> getProductsOnMarkDown(
//            @RequestParam(required = false) LocalDate startDate,
//            @RequestParam(required = false) LocalDate endDate) {
//
//        if(startDate == null) {
//            startDate = LocalDate.now();
//        }
//
//        if(endDate == null) {
//            endDate = startDate.plusWeeks(1);
//        }
//
//        logger.info("Fetching products for markdown between {} and {}", startDate, endDate);
//        List<Product> products = productService.getProductsOnMarkDownInRange(startDate, endDate);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//
//
//
//}
