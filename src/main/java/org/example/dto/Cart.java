package org.example.dto;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.entity.CartItemEntity;
import org.example.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cartId")
public class Cart {
    private int cartId;
    private Set<CartItem> items = new HashSet<>();
    private User user;

}
