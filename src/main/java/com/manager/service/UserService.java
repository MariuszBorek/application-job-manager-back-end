package com.manager.service;

import com.manager.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Map<Integer, User> users = new HashMap<>();
//    private final Map<Integer, Task> archivedTasks = new HashMap<>();

    private Integer id = 0;
    private Integer projectId = 0;

    private Integer taskId = 0;
    private Integer archiveTaskId = 0;

    private Integer sheetId = 0;

    private Integer noteId = 0;

    public void autoIncrementNoteId() {
        noteId  = noteId  + 1;
    }

    public void autoIncrementSheetId() {
        sheetId  = sheetId  + 1;
    }
    public void autoIncrement() {
        id = id + 1;
    }
    public void autoIncrementProjectId() {
        projectId = projectId + 1;
    }
    public void autoIncrementTaskId() {
        taskId = taskId + 1;
    }

    public void autoIncrementArchiveTaskId() {
        archiveTaskId = archiveTaskId + 1;
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

    private Project getUserProject(String userId, String projectId) {
        User foundUser = users.get(Integer.valueOf(userId));
        return foundUser.getProjects().get(Integer.valueOf(projectId));
    }

    // -------------------- TASKS

    public Task addTask(String userId, String projectId, Task task) {
        autoIncrementTaskId();
        task.setId(taskId);
        Project project = getUserProject(userId, projectId);
        project.getTasks().put(taskId, task);
        return task;
    }

    public Task updateTask(String userId, String projectId, Task task) {
        Project project = getUserProject(userId, projectId);
        Task foundTask = project.getTasks().get(task.getId());
        foundTask.setTopic(task.getTopic());
        foundTask.setText(task.getText());
        foundTask.setDate(task.getDate());
        foundTask.setPriority(task.getPriority());
        foundTask.setExecution(task.getExecution());
        return foundTask;
    }

    public List<Task> findAllTask(String userId, String projectId) {
        Project project = getUserProject(userId, projectId);
        return new ArrayList<>(project.getTasks().values());
    }

    public Task deleteTask(String userId, String projectId, String taskId) {
        Project project = getUserProject(userId, projectId);
        project.getTasks().remove(Integer.valueOf(taskId));
        return null;
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
        Project project = getUserProject(userId, projectId);
        Map<Integer, Task> userTasks = project.getTasks();
        for (Task task : tasks) {
            userTasks.remove(task.getId());
        }
        return tasks;
    }

    // -------------------- SHEETS

    public List<Sheet> findAllSheet(String userId, String projectId) {
        Project project = getUserProject(userId, projectId);
        return new ArrayList<>(project.getSheets().values());
    }

    public Sheet addSheet(String userId, String projectId, Sheet sheet) {
        autoIncrementSheetId();
        sheet.setId(sheetId);
        Project project = getUserProject(userId, projectId);
        project.getSheets().put(sheetId, sheet);
        return sheet;
    }

    public Sheet updateSheet(String userId, String projectId, Sheet sheet) {
        Project project = getUserProject(userId, projectId);
        Sheet foundSheet = project.getSheets().get(sheet.getId());
        foundSheet.setNo(sheet.getNo());
        foundSheet.setDescription(sheet.getDescription());
        foundSheet.setEdition(sheet.getEdition());
        foundSheet.setRevision(sheet.getRevision());
        foundSheet.setType(sheet.getType());
        return foundSheet;
    }

    public Sheet deleteSheet(String userId, String projectId, String sheetId) {
        Project project = getUserProject(userId, projectId);
        project.getSheets().remove(Integer.valueOf(sheetId));
        return null;
    }

    // -------------------- NOTES

    public List<Note> findAllNotes(String userId, String projectId) {
        Project project = getUserProject(userId, projectId);
        return new ArrayList<>(project.getNotes().values());
    }

    public Note addNote(String userId, String projectId, Note note) {
        autoIncrementNoteId();
        note.setId(noteId);
        Project project = getUserProject(userId, projectId);
        project.getNotes().put(noteId, note);
        return note;
    }

    public Note updateNote(String userId, String projectId, Note note) {
        Project project = getUserProject(userId, projectId);
        Note foundNote = project.getNotes().get(note.getId());
        foundNote.setText(note.getText());
        return foundNote;
    }

    public Note deleteNote(String userId, String projectId, String noteId) {
        Project project = getUserProject(userId, projectId);
        project.getNotes().remove(Integer.valueOf(noteId));
        return null;
    }



}
