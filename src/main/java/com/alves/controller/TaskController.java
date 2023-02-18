package com.alves.controller;

import com.alves.model.Task;
import com.alves.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Task>> findAll(){
        return ResponseEntity.ok(repository.findAllByOrderByNameAsc().get());
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Task task){
        return ResponseEntity.ok(repository.save(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id){
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
