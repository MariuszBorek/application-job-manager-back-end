package com.manager.service;

import com.manager.model.Project;
import com.manager.model.Task;
import com.manager.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer id = 0;
    private Integer projectId = 0;
    private Integer taskId = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public void autoIncrementProjectId() {
        projectId = projectId + 1;
    }

    public void autoIncrementTaskId() {
        taskId = taskId + 1;
    }


    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }


    public User addUser(User user) {
        autoIncrement();
        user.setId(id);
        user.setProjects(new HashMap<>());
        users.put(id, user);
        return user;
    }

    public User getUser(String email, String password) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElseThrow();
    }

    public User addProject(String id, Project project) {
        autoIncrementProjectId();
        User foundUser = users.get(Integer.parseInt(id));
        Project newProject = new Project();
        newProject.setId(projectId);
        newProject.setTitle(project.getTitle());
        newProject.setDescription(project.getDescription());
        newProject.setTasks(new HashMap<>());
        newProject.setSheets(new HashMap<>());
        newProject.setNotes(new HashMap<>());
        foundUser.getProjects().put(projectId, newProject);
        return foundUser;
    }


    public List<Project> getUserProjects(String id) {
        return new ArrayList<>(users.get(Integer.parseInt(id)).getProjects().values());
    }

    public Task addTask(String userId, String projectId, Task task) {
        autoIncrementTaskId();
        task.setId(taskId);
        Integer userIdNumber = Integer.valueOf(userId);
        Integer projectIdNumber = Integer.valueOf(projectId);
        User foundUser = users.get(userIdNumber);
        Project project = foundUser.getProjects().get(projectIdNumber);
        project.getTasks().put(taskId, task);
        return task;
    }


    public Task updateTask(String userId, String projectId, Task task) {
        User foundUser = users.get(Integer.valueOf(userId));
        Project project = foundUser.getProjects().get(Integer.valueOf(projectId));
        Task foundTask = project.getTasks().get(task.getId());
        foundTask.setTopic(task.getTopic());
        foundTask.setText(task.getText());
        foundTask.setDate(task.getDate());
        foundTask.setPriority(task.getPriority());
        foundTask.setExecution(task.getExecution());
        return foundTask;
    }

    public List<Task> findAllTask(String userId, String projectId) {
        User foundUser = users.get(Integer.valueOf(userId));
        Project project = foundUser.getProjects().get(Integer.valueOf(projectId));
        return new ArrayList<>(project.getTasks().values());
    }

    public Task deleteTask(String userId, String projectId, String taskId) {
        User foundUser = users.get(Integer.valueOf(userId));
        Project project = foundUser.getProjects().get(Integer.valueOf(projectId));
        project.getTasks().remove(Integer.valueOf(taskId));
        return null;
    }
}
