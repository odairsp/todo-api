package com.odr.todo.app.service.impl;

import com.odr.todo.app.model.Task;
import com.odr.todo.app.model.User;
import com.odr.todo.app.repository.TaskRepository;
import com.odr.todo.app.repository.UserRepository;
import com.odr.todo.app.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
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
    public void delete(Long userID, Long taskId) {

        Optional<User> user = userRepository.findById(userID);
        Task task = taskRepository.findById(taskId).orElseThrow(NoSuchElementException::new);
        if (user.isPresent()){
            task.setUser(null);
            task.setCompleted(true);

        }else{
            throw new NoSuchElementException();
        }
        taskRepository.save(task);
        taskRepository.delete(task);
    }
}
