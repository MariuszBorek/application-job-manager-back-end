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

    @PostMapping("/{userId}/{projectId}")
    public Task addTaskToProject(@PathVariable("userId") final String userId,
                                 @PathVariable("projectId") final String projectId,
                                 @RequestBody final Task task) {
        return taskService.addTask(userId, projectId, task);
    }


    @GetMapping("/{userId}/{projectId}")
    public List<Task> findAllTasks(@PathVariable("userId") final String userId,
                                   @PathVariable("projectId") final String projectId) {
        return taskService.findAllTasks(userId, projectId);
    }

    @PutMapping("/{userId}/{projectId}")
    public Task updateTask(@PathVariable("userId") final String userId,
                           @PathVariable("projectId") final String projectId,
                           @RequestBody final Task task) {
        return taskService.updateTask(userId, projectId, task);
    }


    @DeleteMapping("/{userId}/{projectId}/{taskId}")
    public void deleteTask(@PathVariable("userId") final String userId,
                           @PathVariable("projectId") final String projectId,
                           @PathVariable("taskId") final String taskId) {
        taskService.deleteTask(userId, projectId, taskId);
    }

// TODO: add archive list for tasks
//    @GetMapping("/project/task/archive/{userId}/{projectId}")
//    public List<Task> getArchivedTask(@PathVariable("userId") final String userId,
//                                      @PathVariable("projectId") final String projectId) {
//        return userService.getArchiveTasks(userId, projectId);
//    }

    
    @PostMapping("/finished/{userId}/{projectId}")
    public List<Task> clearFinishedTasks(@PathVariable("userId") final String userId,
                                         @PathVariable("projectId") final String projectId,
                                         @RequestBody final List<Task> tasks) {
        return taskService.deleteFinishedTask(userId, projectId, tasks);
    }
}
