package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.Product;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseEntity {
    private List<Product> content;
    private int pageNumber;
    private int pageSize;
    private boolean lastPage;

}
