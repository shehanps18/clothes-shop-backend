package org.example.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.example.entity.CartEntity;
import org.example.entity.ProductEntity;

public class CartItem {
    private int cartItemId;
    private int quantity;
    private double totalPrice;
    private Cart cart;
    private Product product;

}
