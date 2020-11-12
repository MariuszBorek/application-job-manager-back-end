package com.manager.controller;

import com.manager.model.Project;
import com.manager.model.Sheet;
import com.manager.model.Task;
import com.manager.model.User;
import com.manager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAllSheets() {
        return userService.findAllUsers();
    }

    @GetMapping("/login/{email}/{password}")
    public User getUser(@PathVariable("email") final String email,
                        @PathVariable("password") final String password) {
        return userService.getUser(email, password);
    }

    @PostMapping
    public User addUser(@RequestBody final User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{id}")
    public User addUser(@PathVariable("id") final String id,
                        @RequestBody final Project project) {
        return userService.addProject(id, project);
    }

    @PostMapping("/project/task/{userId}/{projectId}")
    public Task addTaskToProject(@PathVariable("userId") final String userId,
                                 @PathVariable("projectId") final String projectId,
                                 @RequestBody final Task task) {
        return userService.addTask(userId, projectId, task);
    }

    @GetMapping("/projects/{id}")
    public List<Project> getUser(@PathVariable("id") final String id) {
        return userService.getUserProjects(id);
    }

    @PutMapping("/project/task/{userId}/{projectId}")
    public Task updateTask(@PathVariable("userId") final String userId,
                           @PathVariable("projectId") final String projectId,
                           @RequestBody final Task task) {
        return userService.updateTask(userId, projectId, task);
    }

    @GetMapping("/project/task/{userId}/{projectId}")
    public List<Task> findAllTasks(@PathVariable("userId") final String userId,
                                   @PathVariable("projectId") final String projectId) {
        return userService.findAllTask(userId, projectId);
    }

    @DeleteMapping("/project/task/{userId}/{projectId}/{taskId}")
    public Task deleteTask(@PathVariable("userId") final String userId,
                           @PathVariable("projectId") final String projectId,
                           @PathVariable("taskId") final String taskId) {
        return userService.deleteTask(userId, projectId, taskId);
    }

}
