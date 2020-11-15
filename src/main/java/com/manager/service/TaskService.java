package com.manager.service;

import com.manager.model.Project;
import com.manager.model.Task;
import com.manager.model.User;
import com.manager.repository.ProjectRepository;
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

    public Task addTask(String userId, String projectId, Task task) {
        User foundUser = userRepository.findById(Integer.parseInt(userId)).orElseThrow();
        Project foundProject = foundUser.getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow();

        task.setProject(foundProject);
        taskRepository.save(task);
        return task;
    }

    public List<Task> findAllTasks(String userId, String projectId) {
        User foundUser = userRepository.findById(Integer.parseInt(userId)).orElseThrow();
        Project foundProject = foundUser.getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow();
        return foundProject.getTasks();
    }

    public Task updateTask(String userId, String projectId, Task task) {
        Task foundTask = getTask(userId, projectId, task.getId().toString());
        foundTask.setTopic(task.getTopic());
        foundTask.setText(task.getText());
        foundTask.setDate(task.getDate());
        foundTask.setPriority(task.getPriority());
        foundTask.setExecution(task.getExecution());
        taskRepository.save(foundTask);
        return foundTask;
    }

    public void deleteTask(String userId, String projectId, String taskId) {
        taskRepository.delete(getTask(userId, projectId, taskId));
    }

// TODO: Fix it! problem: replace lists in all users
//    public List<Task> getArchiveTasks(String userId, String projectId) {
//        Project project = getUserProject(userId, projectId);
//        ArrayList<Task> tasks = new ArrayList<>(project.getTasks().values());
//        for (Task task : tasks) {
//            if(task.getExecution().equals(true)) {
//                archivedTasks.put(task.getId(), task);
//            }
//        }
//        return new ArrayList<>(archivedTasks.values());
//    }

    public List<Task> deleteFinishedTask(String userId, String projectId, List<Task> tasks) {
        for (Task task : tasks) {
            if(task.getExecution().equals(true)) {
                taskRepository.delete(task);
            }
        }
        return getUserProject(userId, projectId).getTasks();
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
