package org.example.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart Item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;
    private int quantity;
    private double totalPrice;

    @JsonBackReference
    @ManyToOne
    private CartEntity cart;
    @OneToOne
    private ProductEntity product;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemEntity)) return false;
        CartItemEntity that = (CartItemEntity) o;
        return Objects.equals(id, that.cartItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
