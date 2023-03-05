package com.alves.service;

import com.alves.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<List<Task>> findAllByOrderByNameAsc();
    Task save(Task task);
    void deleteById(Integer id);

}
