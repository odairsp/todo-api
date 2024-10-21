package com.odr.todo.app.service;

import com.odr.todo.app.model.Task;

public interface TaskService {


    Iterable<Task> findAll();

    Task findById(Long id);

    Task insert(Task cliente);

    Task update(Long id, Task cliente);

    void delete(Long id);
}
