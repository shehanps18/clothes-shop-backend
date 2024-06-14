package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.ProductEntity;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private List<Product> content;
    private int pageNumber;
    private int pageSize;
    private boolean lastPage;
    private int totalPages;

}
