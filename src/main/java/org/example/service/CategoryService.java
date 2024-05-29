package org.example.service;

import org.example.dto.Category;
import org.example.dto.Product;
import org.example.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    void createCategory(Category category);

    CategoryEntity updateCategory(Long categoryId, Category category);

    void deleteById(Long categoryId);

    Product viewProductById(Long categoryId);


    List<CategoryEntity> viewAll();
}
