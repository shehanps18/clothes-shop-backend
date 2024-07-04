package org.example.repository;

import org.example.entity.CartEntity;
import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    Optional<CartEntity> findByUser(UserEntity user);
}
