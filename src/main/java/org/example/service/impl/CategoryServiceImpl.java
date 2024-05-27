package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Category;
import org.example.entity.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.example.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository repository;
    final ModelMapper mapper;

    @Override
    public void createCategory(Category category) {
        CategoryEntity entity= mapper.map(category,CategoryEntity.class);
        repository.save(entity);

    }
}
