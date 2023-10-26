
package com.inventory.inventorymanager.service;

import com.inventory.inventorymanager.model.Product;
import com.inventory.inventorymanager.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testDisplayProductsInMarkDown() {
        LocalDate today = LocalDate.now();
        Product product1 = new Product();
        product1.setExpiryDate(today.plusDays(10));
        product1.setTimeDurationForMarkDown(15);

        Product product2 = new Product();
        product2.setExpiryDate(today.plusDays(5));
        product2.setTimeDurationForMarkDown(7);

        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findPastMarkdownDate(any(LocalDate.class))).thenReturn(products);

        List<Product> result = productService.displayProductsInMarkDown();
        assertEquals(2, result.size());
    }

    @Test
    public void testDisplayProductsForMarkDown() {
        LocalDate today = LocalDate.now();
        Product product1 = new Product();
        product1.setExpiryDate(today.plusDays(10));
        product1.setTimeDurationForMarkDown(3);

        Product product2 = new Product();
        product2.setExpiryDate(today.plusDays(17));
        product2.setTimeDurationForMarkDown(10);

        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findForMarkDownWithinWeek(any(LocalDate.class), any(LocalDate.class))).thenReturn(products);

        List<Product> result = productService.displayProductsForMarkDown();
        assertEquals(2, result.size());
    }
}

