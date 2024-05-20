package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Product;
import org.example.entity.ProductEntity;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);

    }
    @GetMapping("/view-all-product")
    public List<ProductEntity> viewAllProduct(){
        return productService.viewAll();
    }
    @GetMapping("/{id}")
    public Product viewProductById(@PathVariable Long id){
        return productService.viewProductById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
    @PutMapping("/update/{id}")
    public ProductEntity updateProduct(@PathVariable Long id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }
}
