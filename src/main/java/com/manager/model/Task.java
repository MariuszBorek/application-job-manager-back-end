package com.manager.model;


import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
public class Task {

    private Integer id;
    private String topic;
    private String text;
    private Date date;
    private Boolean priority;
    private Boolean execution;

    public Task(Integer id, String topic, String text, Date date, Boolean priority, Boolean execution) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.date = date;
        this.priority = priority;
        this.execution = execution;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
