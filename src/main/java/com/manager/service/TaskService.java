package com.manager.service;

import com.manager.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Task> archivedTasks = new HashMap<>();
    private Integer id = 0;

    public List<Task> findAllTask() {
        return new ArrayList<>(tasks.values());
    }

    public Task addTask(Task task) {
        autoIncrement();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public Task deleteTask(Integer id) {
        Task task = tasks.get(id);
        tasks.remove(task.getId());
        return task;
    }

    public void autoIncrement() {
        id = id + 1;
    }

    public Task updateTask(Task task) {
        Task foundTask = tasks.get(task.getId());
        foundTask.setTopic(task.getTopic());
        foundTask.setText(task.getText());
        foundTask.setDate(task.getDate());
        foundTask.setPriority(task.getPriority());
        foundTask.setExecution(task.getExecution());
        return foundTask;
    }

    // ------------------------------------------------------------

    public List<Task> getArchiveTasks() {
        for (Task task : tasks.values()) {
            if(task.getExecution().equals(true)) {
                archivedTasks.put(task.getId(), task);
            }
        }
        return new ArrayList<>(archivedTasks.values());
    }

    public List<Task> deleteFinishedTask(List<Task> tasks) {
        for (Task task : tasks) {
            this.tasks.remove(task.getId());
        }
        return tasks;
    }
}
