package com.gsf.todo.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gsf.todo.api.error.TaskNotFoundException;
import com.gsf.todo.model.Task;
import com.gsf.todo.repository.TaskRepository;

@RestController
@RequestMapping("/todo")
@Validated
public class TaskController {

	@Autowired
	private TaskRepository repository;

	@GetMapping
	List<Task> findAll() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Task newTask(@Valid @RequestBody Task newTask) {
		return repository.save(newTask);
	}

	@GetMapping("{id}")
	Task findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

	@PutMapping("{id}")
	Task saveOrUpdate(@RequestBody Task newTask, @PathVariable Long id) {

		return repository.findById(id).map(x -> {
			x.setDescription(newTask.getDescription());
			x.setStatus(newTask.getStatus());
			return repository.save(x);
		}).orElseGet(() -> {
			newTask.setId(id);
			return repository.save(newTask);
		});
	}

	@DeleteMapping("{id}")
	void deleteTask(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
