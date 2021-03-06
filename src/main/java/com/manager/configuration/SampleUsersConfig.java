package com.manager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Profile("dev")
//@ConfigurationProperties(prefix = "jm.config")
public class SampleUsersConfig {

    @Value("${jm.config.numberOfUsers}")
    private Integer numberOfUsers;
    @Value("${jm.config.numberOfProjects}")
    private Integer numberOfProjects;
    @Value("${jm.config.numberOfTasks}")
    private Integer numberOfTasks;
    @Value("${jm.config.numberOfSheets}")
    private Integer numberOfSheets;
    @Value("${jm.config.numberOfNotes}")
    private Integer numberOfNotes;
    @Value("${jm.config.numberOfScuppers}")
    private Integer numberOfScuppers;
    @Value("${jm.config.mailPrefix}")
    private String mailPrefix;

    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(Integer numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public Integer getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(Integer numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

    public Integer getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(Integer numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public Integer getNumberOfSheets() {
        return numberOfSheets;
    }

    public void setNumberOfSheets(Integer numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
    }

    public Integer getNumberOfNotes() {
        return numberOfNotes;
    }

    public void setNumberOfNotes(Integer numberOfNotes) {
        this.numberOfNotes = numberOfNotes;
    }

    public Integer getNumberOfScuppers() {
        return numberOfScuppers;
    }

    public void setNumberOfScuppers(Integer numberOfScuppers) {
        this.numberOfScuppers = numberOfScuppers;
    }

    public String getMailPrefix() {
        return mailPrefix;
    }

    public void setMailPrefix(String mailPrefix) {
        this.mailPrefix = mailPrefix;
    }
}
