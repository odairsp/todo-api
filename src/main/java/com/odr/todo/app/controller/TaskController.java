package com.odr.todo.app.controller;


import com.odr.todo.app.model.Task;
import com.odr.todo.app.model.User;
import com.odr.todo.app.service.TaskService;
import com.odr.todo.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Task> create(@PathVariable Long userId, @RequestBody Task task){
        User user = userService.findById(userId);
        if(user != null){
            task.setUser(user);
            Task createdTask = taskService.insert(task);
            return ResponseEntity.ok(createdTask);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


//    @PostMapping("/")
//    public ResponseEntity<Task> createTask(@RequestBody Task taskToCreate){
//        Task taskCreated = taskService.insert(taskToCreate);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(taskCreated.getId())
//                .toUri();
//        return ResponseEntity.created(location).body(taskCreated);
//    }

//    @GetMapping("/")
//    public ResponseEntity<Iterable<Task>> findAll(){
//        return ResponseEntity.ok(taskService.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Task> findById(@PathVariable Long id){
//        return ResponseEntity.ok(taskService.findById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Task> updateById(@PathVariable Long id, @RequestBody Task taskToUpdate){
//        Task taskUpdated = taskService.update(id, taskToUpdate);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("")
//                .buildAndExpand(taskToUpdate.getId())
//                .toUri();
//        return ResponseEntity.created(location).body(taskUpdated);
//    }


}
