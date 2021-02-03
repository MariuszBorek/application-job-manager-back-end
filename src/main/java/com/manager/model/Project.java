package com.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @OneToMany(mappedBy = "project")
    private List<Sheet> sheets;

    @OneToMany(mappedBy = "project")
    private List<Note> notes;

    @OneToMany(mappedBy = "project")
    private List<TaskArchive> taskArchive;

    @OneToMany(mappedBy = "project")
    private List<Scupper> scuppers;

    public Project() {
    }

    public Project(String title,
                   String description,
                   Users user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }


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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<TaskArchive> getTaskArchive() {
        return taskArchive;
    }

    public void setTaskArchive(List<TaskArchive> taskArchives) {
        this.taskArchive = taskArchives;
    }

    public List<Scupper> getScuppers() {
        return scuppers;
    }

    public void setScuppers(List<Scupper> scuppers) {
        this.scuppers = scuppers;
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
