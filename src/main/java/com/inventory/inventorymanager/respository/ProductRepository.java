package com.inventory.inventorymanager.respository;

import com.inventory.inventorymanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//  Interface for performing CRUD operations on the product data, extending JpaRepository.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
