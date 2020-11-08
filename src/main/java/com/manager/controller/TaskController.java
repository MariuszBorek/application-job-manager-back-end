package com.manager.controller;

import com.manager.model.Task;
import com.manager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> findAllTasks() {
        return taskService.findAllTask();
    }

    @PostMapping
    public Task addTask(@RequestBody final Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/update")
    public Task updateTask(@RequestBody final Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable("id") final Integer id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/archive")
    public List<Task> getArchivedTask() {
        return taskService.getArchiveTasks();
    }

    @PostMapping("/finished")
    public List<Task> clearFinishedTasks(@RequestBody final List<Task> tasks) {
        return taskService.deleteFinishedTask(tasks);
    }
}
