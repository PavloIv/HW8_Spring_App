package com.ip.hw8.repository;

import com.ip.hw8.entity.Producer;
import com.ip.hw8.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String productName);
}
