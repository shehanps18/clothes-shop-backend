package org.example.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    private Long id;
    private String product_name;
    private double product_prise;
    private boolean stock;
    private int product_qty;
    private boolean live;
    private String product_imageName;
    private String product_desc;
}
