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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public Boolean getExecution() {
        return execution;
    }

    public void setExecution(Boolean execution) {
        this.execution = execution;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
