package org.example.service;

import org.example.dto.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    User getUserById(Long userId);

    void deleteUserById(Long userId);

    List<User> getAllUsers();
}
