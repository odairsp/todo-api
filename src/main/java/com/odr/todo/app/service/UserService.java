package com.odr.todo.app.service;

import com.odr.todo.app.model.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    User findById(Long id);

    User create(User user);

    User update(Long id, User updatedUser);

    void delete(Long id);
}