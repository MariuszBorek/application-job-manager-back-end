package com.manager.service;

import com.manager.model.Project;
import com.manager.model.Task;
import com.manager.model.TaskArchive;
import com.manager.model.Users;
import com.manager.repository.TaskArchiveRepository;
import com.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskArchiveService {

    private final UserRepository userRepository;
    private final TaskArchiveRepository taskArchiveRepository;

    public TaskArchiveService(UserRepository userRepository, TaskArchiveRepository taskArchiveRepository) {
        this.userRepository = userRepository;
        this.taskArchiveRepository = taskArchiveRepository;
    }

    public List<TaskArchive> addArchivedTasks(String email, String projectId, List<Task> tasks) {
        Users user = userRepository.findByEmail(email);
        List<TaskArchive> taskArchivesList = new ArrayList<>();
        TaskArchive taskArchive = new TaskArchive();
        for (Task task: tasks) {
            if (task.getExecution().equals(true)) {
                taskArchive.setId(task.getId());
                taskArchive.setTopic(task.getTopic());
                taskArchive.setText(task.getText());
                taskArchive.setDate(task.getDate());
                taskArchive.setPriority(task.getPriority());
                taskArchive.setExecution(task.getExecution());
                taskArchive.setProject(getUserProject(user.getId().toString(), projectId));
                taskArchiveRepository.save(taskArchive);
                taskArchivesList.add(taskArchive);
            }

        }
        return getUserProject(user.getId().toString(), projectId).getTaskArchive();
    }

    private Project getUserProject(String userId, String projectId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects()
                .stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow();
    }

    private TaskArchive getArchivedTask(String userId, String projectId, String taskArchivedId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow()
                .getTaskArchive().stream()
                .filter(tskArch -> tskArch.getId().equals(Integer.parseInt(taskArchivedId)))
                .findFirst()
                .orElseThrow();
    }

}
