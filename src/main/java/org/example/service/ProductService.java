package org.example.service;

import org.example.dto.Product;
import org.example.entity.ProductEntity;

import java.util.List;

public interface ProductService {


    Product viewProductById(Long id);

    void createProduct(Product product);

    List<ProductEntity> viewAll();

    void deleteById(Long id);


    ProductEntity updateProduct(Long id, Product product);
}
