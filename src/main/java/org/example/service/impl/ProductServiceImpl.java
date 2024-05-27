package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Product;
import org.example.entity.ProductEntity;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    final ProductRepository repository;
    final ModelMapper mapper;

    @Override
    public Product viewProductById(Long id) {
        Optional<ProductEntity> productEntityOptional = repository.findById(id);
        if (productEntityOptional.isPresent()) {
            return mapper.map(productEntityOptional.get(), Product.class);
        } else {
            log.error("Product with ID {} not found", id);
            return null;
        }
    }

    @Override
    public void createProduct(Product product) {
        ProductEntity entity= mapper.map(product,ProductEntity.class);
        repository.save(entity);

    }

    @Override
    public List<ProductEntity> viewAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductEntity updateProduct(Long id, Product product) {
        ProductEntity existingProduct = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with id "+id));
        mapper.map(product,existingProduct);

        return repository.save(existingProduct);
    }
}
