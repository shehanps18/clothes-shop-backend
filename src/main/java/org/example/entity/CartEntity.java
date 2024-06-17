package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart")
public class CartEntity {
    @Id
    private int cartId;

    @OneToMany
    private Set<CartItemEntity> items = new HashSet<>();

    @OneToOne
    private UserEntity user;

}
