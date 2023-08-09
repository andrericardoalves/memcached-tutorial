package com.alves.controller;

import com.alves.model.Task;
import com.alves.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Task>> findAll(){
        return ResponseEntity.ok(service.findAllByOrderByNameAsc().get());
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Task task){
        return ResponseEntity.ok(service.save(task));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Task task){
        service.update(id, task);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
