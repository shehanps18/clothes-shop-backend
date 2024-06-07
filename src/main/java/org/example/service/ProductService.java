package org.example.service;

import org.example.dto.Product;
import org.example.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {


    Product viewProductById(Long id);

    void createProduct(Product product, double cat_id);

    @Transactional(readOnly = true)
    List<Product> viewAll(int pageNumber, String sortBy, String sortDir);

    void deleteById(Long id);

    ProductEntity updateProduct(Long id, Product product);

    List<Product> viewAll();
}
