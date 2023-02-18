package com.alves.service;

import com.alves.model.Task;
import com.alves.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<List<Task>> findAllByOrderByNameAsc(){
        return repository.findAllByOrderByNameAsc();
    }

}
