package com.odr.todo.app.controller;


import com.odr.todo.app.model.Task;
import com.odr.todo.app.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task taskToCreate){
        Task taskCreated = taskService.insert(taskToCreate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(taskCreated);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Task>> findAll(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateById(@PathVariable Long id, @RequestBody Task taskToUpdate){
        Task taskUpdated = taskService.update(id, taskToUpdate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(taskToUpdate.getId())
                .toUri();
        return ResponseEntity.created(location).body(taskUpdated);
    }
}
