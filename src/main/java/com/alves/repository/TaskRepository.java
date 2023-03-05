package com.alves.repository;

import com.alves.model.Task;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<List<Task>> findAllByOrderByNameAsc();
}
