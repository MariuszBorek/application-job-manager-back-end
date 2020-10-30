package com.manager.model;

import java.util.Map;
import java.util.Objects;

public class Project {

    private Integer id;
    private String title;
    private String description;
    private Map<Integer, Task> tasks;
    private Map<Integer, Sheet> sheets;
    private Map<Integer, Note> notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public void setTasks(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public Map<Integer, Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(Map<Integer, Sheet> sheets) {
        this.sheets = sheets;
    }

    public Map<Integer, Note> getNotes() {
        return notes;
    }

    public void setNotes(Map<Integer, Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(title, project.title) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
