package org.example.repository;

import org.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @EntityGraph(attributePaths = "category")
    List<ProductEntity> findAll();
    List<ProductEntity> findByCategoryCategoryId(Long categoryId);
}
