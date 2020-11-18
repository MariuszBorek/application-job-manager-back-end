package com.manager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Integer id;
    private String topic;
    private String text;
    private LocalDate date;
    private Boolean priority;
    private Boolean execution;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    public Task() {
    }

    public Task(String topic, String text, LocalDate date, Boolean priority, Boolean execution, Project project) {
        this.topic = topic;
        this.text = text;
        this.date = date;
        this.priority = priority;
        this.execution = execution;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public Task setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public Task setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getText() {
        return text;
    }

    public Task setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Task setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Boolean getPriority() {
        return priority;
    }

    public Task setPriority(Boolean priority) {
        this.priority = priority;
        return this;
    }

    public Boolean getExecution() {
        return execution;
    }

    public Task setExecution(Boolean execution) {
        this.execution = execution;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Task setProject(Project project) {
        this.project = project;
        return this;
    }

    public Task build() {
        Task task = new Task();
        task.topic = this.topic;
        task.text = this.text;
        task.date = this.date;
        task.priority = this.priority;
        task.execution = this.execution;
        task.project = this.project;
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(topic, task.topic) &&
                Objects.equals(text, task.text) &&
                Objects.equals(date, task.date) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(execution, task.execution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, text, date, priority, execution);
    }
}
