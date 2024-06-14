package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.User;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    final UserRepository repository;
    final ModelMapper mapper;
    private final UserRepository userRepository;

    @Override
    public void createUser(User user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        userEntity.setDate(new Date());
        userEntity.setActive(true);
        userRepository.save(userEntity);
    }
}
