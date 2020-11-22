package com.manager.controller;

import com.manager.model.Task;
import com.manager.model.TaskArchive;
import com.manager.service.TaskArchiveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects/tasks/archive")
@CrossOrigin("http://localhost:4200")
public class TaskArchiveController {

    private final TaskArchiveService taskArchiveService;

    public TaskArchiveController(TaskArchiveService taskArchiveService) {
        this.taskArchiveService = taskArchiveService;
    }

    @PostMapping("/{userId}/{projectId}")
    public List<TaskArchive> clearFinishedTasks(@PathVariable("userId") final String userId,
                                                @PathVariable("projectId") final String projectId,
                                                @RequestBody final List<Task> tasks) {
        return taskArchiveService.addArchivedTasks(userId, projectId, tasks);
    }

}
