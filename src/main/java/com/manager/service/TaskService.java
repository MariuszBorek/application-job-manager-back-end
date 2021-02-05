package com.manager.service;

import com.manager.model.Project;
import com.manager.model.Task;
import com.manager.model.Users;
import com.manager.repository.TaskRepository;
import com.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task addTask(String email, String projectId, Task task) {
        Users foundUser = userRepository.findByEmail(email);
        Project foundProject = foundUser.getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow();

        task.setProject(foundProject);
        taskRepository.save(task);
        return task;
    }

    public List<Task> findAllTasks(String email, String projectId) {
        Users foundUser = userRepository.findByEmail(email);
        Project foundProject = foundUser.getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow();
        return foundProject.getTasks();
    }

    public Task updateTask(String email, String projectId, Task task) {
        Users user = userRepository.findByEmail(email);
        Task foundTask = getTask(user.getId().toString(), projectId, task.getId().toString());
        foundTask.setTopic(task.getTopic());
        foundTask.setText(task.getText());
        foundTask.setDate(task.getDate());
        foundTask.setPriority(task.getPriority());
        foundTask.setExecution(task.getExecution());
        taskRepository.save(foundTask);
        return foundTask;
    }

    public void deleteTask(String email, String projectId, String taskId) {
        Users user = userRepository.findByEmail(email);
        taskRepository.delete(getTask(user.getId().toString(), projectId, taskId));
    }

    public List<Task> deleteFinishedTask(String email, String projectId, List<Task> tasks) {
        Users user = userRepository.findByEmail(email);
        for (Task task : tasks) {
            if(task.getExecution().equals(true)) {
                taskRepository.delete(task);
            }
        }
        return getUserProject(user.getId().toString(), projectId).getTasks();
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

    private Task getTask(String userId, String projectId, String taskId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow()
                .getTasks().stream()
                .filter(tsk -> tsk.getId().equals(Integer.parseInt(taskId)))
                .findFirst()
                .orElseThrow();
    }


}
