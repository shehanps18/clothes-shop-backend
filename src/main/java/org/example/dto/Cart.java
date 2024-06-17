package org.example.dto;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.CartItemEntity;
import org.example.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    private int cartId;

    @OneToMany
    private Set<CartItem> items = new HashSet<>();

    @OneToOne
    private User user;

}
