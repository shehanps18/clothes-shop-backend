package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cartItemId")
public class CartItem {
    private int cartItemId;
    private int quantity;
    private double totalPrice;
    @JsonIgnore
    private Cart cart;
    private Product product;

}
