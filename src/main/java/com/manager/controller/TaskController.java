package com.manager.controller;

import com.manager.model.Task;
import com.manager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects/tasks")
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{email}/{projectId}")
    public Task addTaskToProject(@PathVariable("email") final String email,
                                 @PathVariable("projectId") final String projectId,
                                 @RequestBody final Task task) {
        return taskService.addTask(email, projectId, task);
    }


    @GetMapping("/{email}/{projectId}")
    public List<Task> findAllTasks(@PathVariable("email") final String email,
                                   @PathVariable("projectId") final String projectId) {
        return taskService.findAllTasks(email, projectId);
    }

    @PutMapping("/{email}/{projectId}")
    public Task updateTask(@PathVariable("email") final String email,
                           @PathVariable("projectId") final String projectId,
                           @RequestBody final Task task) {
        return taskService.updateTask(email, projectId, task);
    }


    @DeleteMapping("/{email}/{projectId}/{taskId}")
    public void deleteTask(@PathVariable("email") final String email,
                           @PathVariable("projectId") final String projectId,
                           @PathVariable("taskId") final String taskId) {
        taskService.deleteTask(email, projectId, taskId);
    }
    
    @PostMapping("/finished/{email}/{projectId}")
    public List<Task> clearFinishedTasks(@PathVariable("email") final String email,
                                         @PathVariable("projectId") final String projectId,
                                         @RequestBody final List<Task> tasks) {
        return taskService.deleteFinishedTask(email, projectId, tasks);
    }
}
