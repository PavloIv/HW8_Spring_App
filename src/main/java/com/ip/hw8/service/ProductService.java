package com.ip.hw8.service;

import com.ip.hw8.entity.Producer;
import com.ip.hw8.entity.Product;
import com.ip.hw8.repository.ProducerRepository;
import com.ip.hw8.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public void createProduct(String productName, BigDecimal productPrice, Long producerId){
        Producer producer;
        producer = producerRepository.getReferenceById(producerId);
        Product product = new Product();
        product.setName(productName);
        product.setPrice(productPrice);
        product.setProducer(producer);

        productRepository.save(product);
    }

    public void updateProduct(Long productId,String productName, BigDecimal productPrice, Long producerId){
        Producer producer;
        producer = producerRepository.getReferenceById(producerId);

        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setPrice(productPrice);
        product.setProducer(producer);

        productRepository.save(product);
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

}
