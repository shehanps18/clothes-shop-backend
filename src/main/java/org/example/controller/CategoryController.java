package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Category;
import org.example.dto.Product;
import org.example.entity.CategoryEntity;
import org.example.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    final CategoryService categoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
    }

    @PutMapping("/update-cat/{id}")
    public CategoryEntity updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping("/delete/{id}")
    public  void deleteById(@PathVariable Long categoryId){
        categoryService.deleteById(categoryId);
    }
    @GetMapping("/{id}")
    public Product viewProductById(@PathVariable Long categoryId){
        return categoryService.viewProductById(categoryId);
    }
    @GetMapping("/getall")
    public List<CategoryEntity> getAllCategories(){
        return categoryService.viewAll();
    }
}
