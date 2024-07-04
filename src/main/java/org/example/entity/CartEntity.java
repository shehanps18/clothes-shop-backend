package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @JsonManagedReference
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItemEntity> items = new HashSet<>();

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (!(o instanceof CartEntity)) return false;
        CartEntity that = (CartEntity) o;
        return Objects.equals(id, that.cartId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @OneToOne
    private UserEntity user;

}
