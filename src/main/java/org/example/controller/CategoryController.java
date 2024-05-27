package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Category;
import org.example.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    final CategoryService categoryService;

    @PostMapping("/create-cat")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
    }
}
