package com.ntsebryk.timemate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ntsebryk.timemate.domain.Task;
import com.ntsebryk.timemate.repository.TaskRepository;
import com.ntsebryk.timemate.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskService taskService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addTask(@Valid @RequestBody Task task) {
		taskService.createTask(task);
	}
	
	@GetMapping
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Task getTaskById(@PathVariable String id) {
		return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("Resourse with id %s not found", id)));
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteTaskById(@PathVariable String id) {
		 taskRepository.deleteById(id);
	}

}
