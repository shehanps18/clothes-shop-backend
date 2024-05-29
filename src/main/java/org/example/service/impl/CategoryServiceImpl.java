package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.controller.ProductController;
import org.example.dto.Category;
import org.example.dto.Product;
import org.example.entity.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.example.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository repository;
    final ModelMapper mapper;
    private final ProductController productController;

    @Override
    public void createCategory(Category category) {
        CategoryEntity entity = mapper.map(category, CategoryEntity.class);
        repository.save(entity);

    }

    @Override
    public CategoryEntity updateCategory(Long categoryId, Category category) {
        Optional<CategoryEntity> optionalCategoryEntity = repository.findById(categoryId);
        if(optionalCategoryEntity.isPresent()){
            CategoryEntity entity= optionalCategoryEntity.get();
            entity.setTitle(category.getTitle());
            repository.save(entity);
            return entity;
        }else {
            throw new RuntimeException("Category not found with id" + categoryId);

        }
    }

    @Override
    public void deleteById(Long categoryId) {
        if(repository.existsById(categoryId)){
            repository.deleteById(categoryId);
        }else {
            throw new RuntimeException("Category not found with id "+categoryId);
        }
    }

    @Override
    public Product viewProductById(Long categoryId) {
        Optional<CategoryEntity> optionalCategory =repository.findById(categoryId);
        if(optionalCategory.isPresent()){
            return  mapper.map(optionalCategory.get(), Product.class);
        }else{
            throw new RuntimeException("category not found with id "+categoryId);
        }
    }

    @Override
    public List<CategoryEntity> viewAll() {
        return repository.findAll();
    }


}
