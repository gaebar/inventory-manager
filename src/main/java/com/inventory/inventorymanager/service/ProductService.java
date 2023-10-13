package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.exceptions.ProductAlreadyExistsException;
import com.inventory.inventorymanager.exceptions.ProductNotFoundException;
import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
    }

    @Transactional
    public Product createProduct(Product product) {
        if (productRepository.existsByProductId(product.getProductID())) {
            throw new ProductAlreadyExistsException("Product with the ID " + product.getProductID() + " already exists");
        }

        if(product.getExpiryDate() == null) {
            product.setExpiryDate(LocalDate.now().plusMonths(3));
        }

        if(product.getTimeDurationForMarkDown() == null) {
            long daysToMarkDown = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), product.getExpiryDate());
            product.setTimeDurationForMarkDown((int)daysToMarkDown - 6);
        }

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public Product updateProduct(Long id, Product newProduct) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        newProduct.setProductID(id);
        return productRepository.save(newProduct);
    }
}
