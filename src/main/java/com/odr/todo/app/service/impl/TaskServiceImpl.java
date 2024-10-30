package com.odr.todo.app.service.impl;

import com.odr.todo.app.model.Task;
import com.odr.todo.app.repository.TaskRepository;
import com.odr.todo.app.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Task insert(Task task) {

        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, Task task) {

        return taskRepository
                .findById(id).map(existisTask -> {
                    existisTask.setTitle(task.getTitle());
                    existisTask.setDescription(task.getDescription());
                    existisTask.setCompleted(task.isCompleted());
                    if (existisTask.isCompleted() && existisTask.getCompletedAt() == null) {
                        existisTask.setCompletedAt(LocalDateTime.now());
                    }

                    return taskRepository.save(existisTask);
                })
                .orElseThrow(NoSuchElementException::new);

    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
        task.setUser(null);
        taskRepository.delete(task);
    }
}
