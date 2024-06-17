package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart Item")
public class CartItemEntity {
    @Id
    private int cartItemId;
    private int quantity;
    private double totalPrice;


    @ManyToOne
    private CartEntity cart;
    @OneToOne
    private ProductEntity product;
}
