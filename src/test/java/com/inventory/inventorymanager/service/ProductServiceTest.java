//
//package com.inventory.inventorymanager.service;
//
//import com.inventory.inventorymanager.model.Product;
//import com.inventory.inventorymanager.repository.ProductRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private ProductService productService;
//
//    @Test
//    public void testDisplayProductsInMarkDown() {
//        LocalDate today = LocalDate.now();
//        Product product1 = new Product();
//        product1.setExpiryDate(today.plusDays(10));
//        product1.setTimeDurationForMarkDown(15);  // Expiry date - 15 days = today - 5 days
//
//        Product product2 = new Product();
//        product2.setExpiryDate(today.plusDays(5));
//        product2.setTimeDurationForMarkDown(2);  // Expiry date - 2 days = today + 3 days
//
//        List<Product> products = Arrays.asList(product1, product2);
//        when(productRepository.findAll()).thenReturn(products);
//
//        List<Product> result = productService.displayProductsInMarkDown();
//        assertEquals(1, result.size());  // Expecting only product1 to be past its markdown date
//    }
//
//    @Test
//    public void testDisplayProductsForMarkDown() {
//        LocalDate today = LocalDate.now();
//        Product product1 = new Product();
//        product1.setExpiryDate(today.plusDays(10));
//        product1.setTimeDurationForMarkDown(3);  // Expiry date - 3 days = today + 7 days
//
//        Product product2 = new Product();
//        product2.setExpiryDate(today.plusDays(17));
//        product2.setTimeDurationForMarkDown(10);  // Expiry date - 10 days = today + 7 days
//
//        List<Product> products = Arrays.asList(product1, product2);
//
//        when(productRepository.findAll()).thenReturn(products);  // Mock the findAll method to return your list of products
//
//        List<Product> result = productService.displayProductsForMarkDown();
//        assertEquals(2, result.size());  // Expecting both products to be marked down within a week
//    }
//
//}
//
