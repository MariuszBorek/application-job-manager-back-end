package com.manager.controller;

import com.manager.model.Task;
import com.manager.model.TaskArchive;
import com.manager.service.TaskArchiveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects/tasks/archive")
@CrossOrigin("*")
public class TaskArchiveController {

    private final TaskArchiveService taskArchiveService;

    public TaskArchiveController(TaskArchiveService taskArchiveService) {
        this.taskArchiveService = taskArchiveService;
    }

    @PostMapping("/{email}/{projectId}")
    public List<TaskArchive> clearFinishedTasks(@PathVariable("email") final String email,
                                                @PathVariable("projectId") final String projectId,
                                                @RequestBody final List<Task> tasks) {
        return taskArchiveService.addArchivedTasks(email, projectId, tasks);
    }

}
