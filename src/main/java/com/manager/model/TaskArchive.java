package com.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class TaskArchive {

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

    public TaskArchive() {
    }

    public TaskArchive(String topic, String text, LocalDate date, Boolean priority, Boolean execution, Project project) {
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

    public TaskArchive setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public TaskArchive setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getText() {
        return text;
    }

    public TaskArchive setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public TaskArchive setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Boolean getPriority() {
        return priority;
    }

    public TaskArchive setPriority(Boolean priority) {
        this.priority = priority;
        return this;
    }

    public Boolean getExecution() {
        return execution;
    }

    public TaskArchive setExecution(Boolean execution) {
        this.execution = execution;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public TaskArchive setProject(Project project) {
        this.project = project;
        return this;
    }

    public TaskArchive build() {
        TaskArchive taskArchive = new TaskArchive();
        taskArchive.topic = this.topic;
        taskArchive.text = this.text;
        taskArchive.date = this.date;
        taskArchive.priority = this.priority;
        taskArchive.execution = this.execution;
        taskArchive.project = this.project;
        return taskArchive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskArchive that = (TaskArchive) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(execution, that.execution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, text, date, priority, execution);
    }
}
