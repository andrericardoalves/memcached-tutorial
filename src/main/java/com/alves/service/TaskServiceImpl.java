package com.alves.service;

import com.alves.model.Task;
import com.alves.repository.TaskRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }
    @Override
    @Cacheable(cacheNames ="taskCached")
    public Optional<List<Task>> findAllByOrderByNameAsc(){
        return repository.findAllByOrderByNameAsc();
    }

    @Override
    @CacheEvict(value ="taskCached", allEntries = true)
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return repository.findById(id);
    }


    @Override
    @CachePut(value = "taskCached", key = "#id")
    public void update(Integer id, Task task) {
        Optional<Task> taskFound = findById(id);

        if(taskFound.isPresent()){
            Task taskObj = taskFound.get();
            taskObj.setName(task.getName());
            save(taskObj);
        }
    }

    @Override
    @CacheEvict(value ="taskCached", allEntries = true)
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
