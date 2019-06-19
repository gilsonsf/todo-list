package com.gsf.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsf.todo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
