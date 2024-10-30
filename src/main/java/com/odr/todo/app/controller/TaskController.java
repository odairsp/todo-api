package com.odr.todo.app.controller;


import com.odr.todo.app.model.Task;
import com.odr.todo.app.model.User;
import com.odr.todo.app.repository.TaskRepository;
import com.odr.todo.app.service.TaskService;
import com.odr.todo.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private  TaskRepository taskRepository;



    @PostMapping("user/{userId}")
    public ResponseEntity<Task> create(@PathVariable Long userId, @RequestBody Task task) {
        User user = userService.findById(userId);
        if (user != null) {
            task.setUser(user);
            Task createdTask = taskService.insert(task);
            return ResponseEntity.ok(createdTask);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("user/{userId}/{taskId}")
    public ResponseEntity<Task> delete(@PathVariable Long userId, @PathVariable Long taskId) {

        Task task = taskService.findById(taskId);
        taskService.delete(userId, taskId);

        return ResponseEntity.ok(task);
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
