package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.User;
import org.example.entity.UserEntity;
import org.example.exceptions.UserNotFoundException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    final UserRepository repository;
    final ModelMapper mapper;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createUser(User user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        userEntity.setDate(new Date());
        userEntity.setActive(true);
        userRepository.save(userEntity);
    }

    @Override
    public User getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User not found with id "+ userId));
        return modelMapper.map(userEntity,User.class);
    }

    @Override
    public void deleteUserById(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("User not found with Id "+userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity,User.class))
                .collect(Collectors.toList());

    }
}
