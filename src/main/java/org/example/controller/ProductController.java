package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AppConstants;
import org.example.dto.Product;
import org.example.dto.ProductResponse;
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

    @PostMapping("/create/{cat_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product,@PathVariable double cat_id) {
        productService.createProduct(product,cat_id);

    }
    @GetMapping("/view-all-product")
    public ProductResponse viewAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "productid", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        return productService.viewAll(pageNumber,pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public Product viewProductById(@PathVariable Long id,@RequestParam(value = "pageNumber",
            defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber
            ,@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_Size_STRING
            ,required = false)int pageSize
            ,@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY_STRING
            ,required = false) String sortBy
            ,@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR_STRING
            ,required = false) String sortDir){
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
    @GetMapping("/catBy-Id/{categoryId}")
    public List<Product> getProductByCategoryId(@PathVariable Long categoryId){
        return productService.findProductByCategoryId(categoryId);

    }
}
