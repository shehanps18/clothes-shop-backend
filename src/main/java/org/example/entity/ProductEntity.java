package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String product_name;
    private double product_prise;
    private boolean stock;
    private int product_qty;
    private boolean live;
    private String product_imageName;
    private String product_desc;
}
