package com.manager.service;

import com.manager.model.Project;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProjectService {

    private final Map<Integer, Project> projects = new HashMap<>();
    private Integer id = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public Integer getId() {
        return id;
    }

    public List<Project> findAllProjects() {
        return new ArrayList<>(projects.values());
    }

    public Project addProject(Project project) {
        autoIncrement();
        project.setId(id);
        return projects.put(id, project);
    }

    public Map<Integer, Project> getProjects() {
        return projects;
    }
}
