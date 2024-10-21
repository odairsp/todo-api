package com.odr.todo.app.repository;

import com.odr.todo.app.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
