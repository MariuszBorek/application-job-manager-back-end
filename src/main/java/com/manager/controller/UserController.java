package com.manager.controller;

import com.manager.model.*;
import com.manager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    // -------------------- TASKS

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
// TODO: add archive list for tasks
//    @GetMapping("/project/task/archive/{userId}/{projectId}")
//    public List<Task> getArchivedTask(@PathVariable("userId") final String userId,
//                                      @PathVariable("projectId") final String projectId) {
//        return userService.getArchiveTasks(userId, projectId);
//    }

    @PostMapping("/project/task/finished/{userId}/{projectId}")
    public List<Task> clearFinishedTasks(@PathVariable("userId") final String userId,
                                         @PathVariable("projectId") final String projectId,
                                         @RequestBody final List<Task> tasks) {
        return userService.deleteFinishedTask(userId, projectId, tasks);
    }

    // -------------------- SHEETS

    @GetMapping("project/sheets/{userId}/{projectId}")
    public List<Sheet> findAllSheets(@PathVariable("userId") final String userId,
                                     @PathVariable("projectId") final String projectId) {
        return userService.findAllSheet(userId, projectId);
    }

    @PostMapping("/project/sheets/{userId}/{projectId}")
    public Sheet addSheetToProject(@PathVariable("userId") final String userId,
                                   @PathVariable("projectId") final String projectId,
                                   @RequestBody final Sheet sheet) {
        return userService.addSheet(userId, projectId, sheet);
    }

    @PutMapping("/project/sheets/{userId}/{projectId}")
    public Sheet updateTask(@PathVariable("userId") final String userId,
                            @PathVariable("projectId") final String projectId,
                            @RequestBody final Sheet sheet) {
        return userService.updateSheet(userId, projectId, sheet);
    }

    @DeleteMapping("/project/sheets/{userId}/{projectId}/{sheetId}")
    public Sheet deleteSheet(@PathVariable("userId") final String userId,
                             @PathVariable("projectId") final String projectId,
                             @PathVariable("sheetId") final String sheetId) {
        return userService.deleteSheet(userId, projectId, sheetId);
    }

    // -------------------- NOTES

    @GetMapping("project/notes/{userId}/{projectId}")
    public List<Note> findAllNotes(@PathVariable("userId") final String userId,
                                   @PathVariable("projectId") final String projectId) {
        return userService.findAllNotes(userId, projectId);
    }

    @PostMapping("/project/notes/{userId}/{projectId}")
    public Note addNoteToProject(@PathVariable("userId") final String userId,
                                 @PathVariable("projectId") final String projectId,
                                 @RequestBody final Note note) {
        return userService.addNote(userId, projectId, note);
    }

    @PutMapping("/project/notes/{userId}/{projectId}")
    public Note updateNote(@PathVariable("userId") final String userId,
                           @PathVariable("projectId") final String projectId,
                           @RequestBody final Note note) {
        return userService.updateNote(userId, projectId, note);
    }

    @DeleteMapping("/project/notes/{userId}/{projectId}/{notesId}")
    public Note deleteNote(@PathVariable("userId") final String userId,
                             @PathVariable("projectId") final String projectId,
                             @PathVariable("notesId") final String notesId) {
        return userService.deleteNote(userId, projectId, notesId);
    }

}
