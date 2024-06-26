package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Category;
import org.example.dto.Product;
import org.example.dto.ProductResponse;
import org.example.entity.CategoryEntity;
import org.example.entity.ProductEntity;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public  class ProductServiceImpl implements ProductService {
    final ProductRepository repository;
    final ModelMapper mapper;
    final CategoryRepository categoryRepository;

    @Override
    public Product viewProductById(Long id) {
        Optional<ProductEntity> productEntityOptional = repository.findById(id);
        if (productEntityOptional.isPresent()) {
            ProductEntity productEntity = productEntityOptional.get();
            Product product = mapper.map(productEntity, Product.class);

            // Check if ProductEntity has a category associated with it
            if (productEntity.getCategory() != null) {
                // Map CategoryEntity to Category DTO and set it in Product DTO
                Category category = mapper.map(productEntity.getCategory(), Category.class);
                product.setCategory(category);
            }

            return product;
        } else {
            log.error("Product with ID {} not found", id);
            return null;
        }
    }

    @Override
    public void createProduct(Product product, double cat_id) {
        ProductEntity entity = mapper.map(product, ProductEntity.class);
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById((long) cat_id);
        if (categoryEntityOptional.isPresent()) {
            entity.setCategory(categoryEntityOptional.get());
        } else {
            throw new RuntimeException("category not found with id" + cat_id);
        }
        repository.save(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public ProductResponse viewAll(int pageNumber,int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.trim().equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<ProductEntity> page = repository.findAll(pageable);

        List<Product> products = page.getContent().stream()
                .filter(ProductEntity::isLive)
                .map(this::mapProductEntityToDto)
                .collect(Collectors.toList());

        ProductResponse response = new ProductResponse();
        response.setContent(products);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }



    private Product mapProductEntityToDto(ProductEntity productEntity) {
        Product product = mapper.map(productEntity, Product.class);
        if (productEntity.getCategory() != null) {
            product.setCategory(mapper.map(productEntity.getCategory(), Category.class));
        }
        return product;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductEntity updateProduct(Long id, Product product) {
        ProductEntity existingProduct = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
        mapper.map(product, existingProduct);
        if (product.getCategory() != null) {
            existingProduct.setCategory(mapper.map(product.getCategory(), CategoryEntity.class));
        }
        ProductEntity updateProduct = repository.save(existingProduct);
        return mapper.map(updateProduct, ProductEntity.class);
    }

    @Override
    public List<Product> findProductByCategoryId(Long categoryId) {
        List<ProductEntity> productEntities = repository.findByCategoryCategoryId(categoryId);

        return productEntities.stream()
                .map(this::mapProductEntityToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<Product> viewAll() {
//        List<ProductEntity> productEntities = repository.findAll();
//        return productEntities.stream()
//                .map(this::mapProductEntityToDto)
//                .collect(Collectors.toList());
//    }
    }
